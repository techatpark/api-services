package com.gurukulams.web.starter.security.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gurukulams.core.payload.AuthenticationResponse;
import com.gurukulams.core.payload.RefreshToken;
import com.gurukulams.web.starter.security.config.AppProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import jakarta.servlet.http.HttpServletRequest;
import java.security.Key;
import java.security.Principal;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * The type Token provider.
 */
public class TokenProvider {
    /**
     * hh.
     */
    private static final Logger LOG =
            LoggerFactory.getLogger(TokenProvider.class);
    /**
     * Cache Manager.
     */
    private final CacheManager cacheManager;

    /**
     * Object Mapper.
     */
    private final ObjectMapper objectMapper;

    /**
     * Cache to hold auth tokens.
     */
    private final Cache authCache;
    /***
     * hhh.
     */
    private AppProperties appProperties;

    /**
     * UserDetailsService.
     */
    private UserDetailsService userDetailsService;

    /**
     * gg.
     *
     * @param appPropertie  the app propertie
     * @param aobjectMapper
     * @param acacheManager
     * @param auserDetailsService
     */
    public TokenProvider(final AppProperties appPropertie,
                         final CacheManager acacheManager,
                         final ObjectMapper aobjectMapper,
                         final UserDetailsService auserDetailsService) {
        this.objectMapper = aobjectMapper;
        this.appProperties = appPropertie;
        this.cacheManager = acacheManager;
        this.userDetailsService = auserDetailsService;
        this.authCache = cacheManager.getCache("Auth");
    }

    /**
     * Gets Authentication.
     * @param request
     * @param jwt
     * @return authentication
     */
    public Authentication getAuthentication(final HttpServletRequest request,
                                            final String jwt) {
        final String userName =
                getUserNameFromToken(request, jwt);

        final UserDetails userDetails =
                userDetailsService.loadUserByUsername(userName);
        final UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails, null,
                        userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource()
                .buildDetails(request));
        return authentication;
    }

    /**
     * generate token after login.
     *
     * @param authentication the authentication
     * @return token string
     */
    public String generateToken(final Authentication authentication) {
        String token = UUID.randomUUID().toString();
        long now = System.currentTimeMillis();

        this.authCache.put(token, Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(authentication.getName())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now
                        + appProperties.getAuth().getTokenExpirationMsec()))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact());
        return token;

    }


    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(appProperties.getAuth()
                .getTokenSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * gg.
     *
     * @param token the token
     * @param request
     * @return token. user name from token
     */
    public String getUserNameFromToken(final HttpServletRequest request,
                                       final String token) {


        Cache.ValueWrapper valueWrapper = authCache.get(token);

        if (valueWrapper == null) {
            throw new BadCredentialsException("Invalid Token");
        }

        if (request.getRequestURI().equals("/api/auth/refresh")) {
            return getUserNameFromExpiredToken(valueWrapper.get().toString());
        }

        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(valueWrapper.get().toString())
                .getBody();

        return claims.getSubject();
    }

    /**
     * Gets Username from Expired Token.
     * @param token
     * @return userName
     */
    public String getUserNameFromExpiredToken(final String token)  {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        // Splitting header, payload and signature
        String[] parts = token.split("\\.");
        String headers =
                new String(decoder.decode(parts[0])); // Header
        String payload =
                new String(decoder.decode(parts[1])); // Payload
        String userName;
        try {
            userName = this.objectMapper.readTree(payload).get("sub").asText();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return userName;
    }

    /**
     * ddd.
     * @param request
     * @param token the auth token
     * @return dd. boolean
     */
    private boolean validateToken(final HttpServletRequest request,
                                 final String token) {
        try {
            Cache.ValueWrapper authToken = authCache.get(token);
            if (authToken == null) {
                return false;
            }
            Jwts.parserBuilder()
                    .setSigningKey(getSignInKey()).build()
                    .parseClaimsJws(authToken.get().toString());
            return true;
        } catch (final MalformedJwtException ex) {
            LOG.error("Invalid JWT token");
        } catch (final ExpiredJwtException ex) {
            if (request.getRequestURI().equals("/api/auth/refresh")) {
                return true;
            }
            LOG.error("Expired JWT token");
        } catch (final UnsupportedJwtException ex) {
            LOG.error("Unsupported JWT token");
        } catch (final IllegalArgumentException ex) {
            LOG.error("JWT claims string is empty.");
        }
        return false;
    }

    /**
     * Logs Out user.
     *
     * @param token
     */
    public void logout(final String token) {
        authCache.evict(token);
    }

    /**
     * Generates Refresh Token.
     * @param token
     * @return refreshToken
     */
    public String generateRefreshToken(final String token) {
        String refreshToken = UUID.randomUUID().toString();
        this.authCache.put(refreshToken, token);
        return refreshToken;
    }

    /**
     * refresh.
     * @param principal
     * @param refreshToken
     * @return authenticationResponse
     */
    public AuthenticationResponse refresh(final Principal principal,
                                          final RefreshToken refreshToken) {

        // Cleanup Existing Tokens.
        Cache.ValueWrapper refreshTokenCache = authCache
                .get(refreshToken.getToken());
        if (refreshTokenCache == null) {
            throw new BadCredentialsException("Refresh Token unavailable");
        } else {
            String authToken = refreshTokenCache.get().toString();

            authCache.evict(refreshToken.getToken());
            authCache.evict(authToken);

            final Authentication authResult =
                            new UsernamePasswordAuthenticationToken(
                                    principal.getName(),
                                    principal.getName());

            authToken = generateToken(authResult);

            AuthenticationResponse authenticationResponse =
                    new AuthenticationResponse(principal.getName(),
                            authToken,
                            this.generateRefreshToken(authToken),
                            null);
            return authenticationResponse;
        }

    }
}
