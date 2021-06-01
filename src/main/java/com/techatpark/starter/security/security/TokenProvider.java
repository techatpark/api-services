package com.techatpark.starter.security.security;

import com.techatpark.starter.security.config.AppProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

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
     * gg.
     *
     * @param appPropertie the app propertie
     */
    public TokenProvider(final AppProperties appPropertie) {
        this.appProperties = appPropertie;
    }

    /**
     * generate token after login.
     *
     * @param authentication the authentication
     * @return token string
     */
    public String generateToken(final Authentication authentication) {

        final Date now = new Date();
        final Date expiryDate = new Date(now.getTime()
                + appProperties.getAuth().getTokenExpirationMsec());
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(authentication.getName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, appProperties.getAuth()
                        .getTokenSecret()).compact();

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
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    /**
     * ddd.
     *
     * @param authToken the auth token
     * @return dd. boolean
     */
    public boolean validateToken(final String authToken) {
        try {
            Jwts.parser().setSigningKey(appProperties.getAuth()
                    .getTokenSecret()).parseClaimsJws(authToken);
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

}
