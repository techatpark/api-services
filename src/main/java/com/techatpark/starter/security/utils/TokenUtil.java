package com.techatpark.starter.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Component
public class TokenUtil {

    private String secret = "javatoday";

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()
                        + TimeUnit.MILLISECONDS.convert(Duration.ofMinutes(1))))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Claims parseToken(String token) {
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
