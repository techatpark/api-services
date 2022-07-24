package com.gurukulams.web.starter.security.security;

import com.gurukulams.web.starter.security.config.AppProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * The type Token provider.
 */
@Service
public class TokenProvider {
    /**
     * hh.
     */
    private static final Logger LOG =
            LoggerFactory.getLogger(TokenProvider.class);
    /***
     * hhh.
     */
    private AppProperties appProperties;

    /**
     * Cache Manager.
     */
    private final CacheManager cacheManager;

    /**
     * Cache to hold auth tokens.
     */
    private final Cache authCache;

    /**
     * gg.
     *
     * @param appPropertie the app propertie
     * @param acacheManager
     */
    public TokenProvider(final AppProperties appPropertie,
                         final CacheManager acacheManager) {
        this.appProperties = appPropertie;
        this.cacheManager = acacheManager;
        this.authCache = cacheManager.getCache("Auth");
    }

    /**
     * generate token after login.
     *
     * @param authentication the authentication
     * @return token string
     */
    public String generateToken(final Authentication authentication) {
        String token = UUID.randomUUID().toString();

        final Date now = new Date();
        final Date expiryDate = new Date(now.getTime()
                + appProperties.getAuth().getTokenExpirationMsec());

        this.authCache.put(token, Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(authentication.getName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, appProperties.getAuth()
                        .getTokenSecret()).compact());
        return token;

    }

    /**
     * gg.
     *
     * @param token the token
     * @return token. user name from token
     */
    public String getUserNameFromToken(final String token) {
        final Claims claims = Jwts.parser()
                .setSigningKey(appProperties.getAuth().getTokenSecret())
                .parseClaimsJws(authCache.get(token).get().toString())
                .getBody();

        return claims.getSubject();
    }

    /**
     * ddd.
     *
     * @param token the auth token
     * @return dd. boolean
     */
    public boolean validateToken(final String token) {
        try {
            Cache.ValueWrapper authToken = authCache.get(token);
            if (authToken == null) {
                return false;
            }
            Jwts.parser().setSigningKey(appProperties.getAuth()
                    .getTokenSecret())
                    .parseClaimsJws(authToken.get().toString());
            return true;
        } catch (final SignatureException ex) {
            LOG.error("Invalid JWT signature");
        } catch (final MalformedJwtException ex) {
            LOG.error("Invalid JWT token");
        } catch (final ExpiredJwtException ex) {
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

}
