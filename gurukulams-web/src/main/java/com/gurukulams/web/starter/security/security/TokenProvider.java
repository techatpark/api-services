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

import org.springframework.util.StringUtils;

import java.security.Key;
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
     * value.
     */
    private static final int VALUE = 7;

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
     * @param requestURI
     * @param jwt
     * @return authentication
     */
    public UsernamePasswordAuthenticationToken getAuthentication(
                            final String requestURI,
                            final String jwt) {
        final String userName =
                getUserNameFromToken(requestURI, jwt);

        final UserDetails userDetails =
                userDetailsService.loadUserByUsername(userName);
        final UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails, null,
                        userDetails.getAuthorities());

        return authentication;
    }


    /**
     * generate AuthenticationResponse.
     *
     * @param authentication the authentication
     * @return token string
     */
    public AuthenticationResponse getAuthenticationResponse(
            final Authentication authentication) {
        final String token = generateToken(authentication);

        UserPrincipal userPrincipal =
                (UserPrincipal) userDetailsService
                        .loadUserByUsername(authentication.getName());

        final AuthenticationResponse authenticationResponse =
                new AuthenticationResponse(authentication.getName(),
                        token,
                        appProperties.getAuth().getTokenExpirationMsec(),
                        generateRefreshToken(token),
                        userPrincipal.getProfilePicture());
        return authenticationResponse;
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
     * @param requestURI
     * @return token. user name from token
     */
    public String getUserNameFromToken(final String requestURI,
                                       final String token) {


        Cache.ValueWrapper valueWrapper = authCache.get(token);

        if (valueWrapper == null) {
            throw new BadCredentialsException("Invalid Token");
        }

        String jwtToken = valueWrapper.get().toString();



        try {
            final Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();
            return claims.getSubject();
        } catch (final MalformedJwtException | UnsupportedJwtException
                       | IllegalArgumentException ex) {
            throw new BadCredentialsException("Invalid Token", ex);
        } catch (final ExpiredJwtException ex) {
            if (requestURI.equals("/api/auth/refresh")) {
                return getUserNameFromExpiredToken(jwtToken);
            } else {
                throw new BadCredentialsException("Expired Token", ex);
            }
        }

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
     * @param token the auth token
     * @return dd. boolean
     */
    private boolean isExpired(final String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSignInKey()).build()
                    .parseClaimsJws(token);
        } catch (final MalformedJwtException | UnsupportedJwtException
                       | IllegalArgumentException ex) {
            throw new BadCredentialsException("Invalid Token", ex);
        } catch (final ExpiredJwtException ex) {
            return true;
        }
        return false;
    }

    /**
     * Logs Out user.
     *
     * @param authHeader
     */
    public void logout(final String authHeader) {
        authCache.evict(getBearer(authHeader));
    }


    private String getBearer(final String authHeader) {
        if (StringUtils.hasText(authHeader) && authHeader
                .startsWith("Bearer ")) {
            return authHeader.substring(VALUE);
        }
        throw new BadCredentialsException("Invalid Token");
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
     * @param authHeader
     * @param userName
     * @param refreshToken
     * @return authenticationResponse
     */
    public AuthenticationResponse refresh(final String authHeader,
                                final String userName,
                                final RefreshToken refreshToken) {

        // Cleanup Existing Tokens.
        Cache.ValueWrapper refreshTokenCache = authCache
                .get(refreshToken.getToken());


        if (refreshTokenCache == null) {
            throw new BadCredentialsException("Refresh Token unavailable");
        } else {
            String authToken = refreshTokenCache.get().toString();

            Cache.ValueWrapper authTokenCache = authCache
                    .get(authToken);

            if (authTokenCache == null) {
                throw new BadCredentialsException("Invalid Token");
            }

            if (!isExpired(authTokenCache.get().toString())) {
                throw new BadCredentialsException("Token is not Expired Yet");
            }

            if (!authToken.equals(getBearer(authHeader))) {
                throw new BadCredentialsException("Tokens are not matching");
            }

            authCache.evict(refreshToken.getToken());
            authCache.evict(authToken);

            return getAuthenticationResponse(userName);
        }

    }

    private AuthenticationResponse getAuthenticationResponse(final String userName) {
        final Authentication authResult =
                new UsernamePasswordAuthenticationToken(
                        userName,
                        userName);

        UserPrincipal userPrincipal =
                (UserPrincipal) userDetailsService
                        .loadUserByUsername(authResult.getName());
        String authToken = generateToken(authResult);
        AuthenticationResponse authenticationResponse =
                new AuthenticationResponse(userName,
                        authToken,
                        appProperties.getAuth().getTokenExpirationMsec(),
                        this.generateRefreshToken(authToken),
                        userPrincipal.getProfilePicture());
        return authenticationResponse;
    }
}
