package com.techatpark.starter.security.security;

import java.util.Date;
import java.util.HashMap;

import com.techatpark.starter.security.config.AppProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

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
     * @param appPropertie
     */
    public TokenProvider(final AppProperties appPropertie) {
        this.appProperties = appPropertie;
    }

    /**
     * generate token after login.
     * @param authentication
     * @return token
     */
    public String generateToken(final Authentication authentication) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime()
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
     * @param token
     * @return token.
     */
    public String getUserNameFromToken(final String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(appProperties.getAuth().getTokenSecret())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    /**
     * ddd.
     * @param authToken
     * @return dd.
     */
    public boolean validateToken(final String authToken) {
        try {
            Jwts.parser().setSigningKey(appProperties.getAuth()
                    .getTokenSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            LOG.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            LOG.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            LOG.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            LOG.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            LOG.error("JWT claims string is empty.");
        }
        return false;
    }

}
