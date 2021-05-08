package com.techatpark.starter.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class TokenUtil {

    /**
     * declare variable secret.
     */
    private final String secret = "javatoday";
    /**
     * declare variable duration.
     */
    private final Integer duration = 120;

    /**
     * generate token after login.
     *
     * @param userDetails
     * @return token
     */
    public String generateToken(final UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()
                        + TimeUnit.MILLISECONDS
                        .convert(Duration.ofMinutes(duration))))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * get the subject details.
     *
     * @param token
     * @return authentication object
     */
    public Authentication getAuthentication(final String token) {
        Claims claims = parseToken(token);
        return new UsernamePasswordAuthenticationToken(
                claims.getSubject(), null, List.of());
    }

    private Claims parseToken(final String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            return body;

        } catch (JwtException | ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }
}
