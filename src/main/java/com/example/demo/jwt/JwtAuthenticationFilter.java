package com.example.demo.jwt;

import java.io.IOException;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    /**
     * ssss.
     */
    private final String jwtAudience;
    /**
     * ssss.
     */
    private final String jwtIssuer;
    /**
     * ssss.
     */
    private final String jwtSecret;
    /**
     * ssss.
     */
    private final String jwtType;

    /**
     * Build.
     * @param jwtIssuer
     * @param jwtSecret
     * @param jwtAudience
     * @param jwtType     * 
     * @param authenticationManager
     */
    public JwtAuthenticationFilter(final AuthenticationManager authenticationManager, final String jwtAudience,
            final String jwtIssuer, final String jwtSecret, final String jwtType) {
        this.jwtAudience = jwtAudience;
        this.jwtIssuer = jwtIssuer;
        this.jwtSecret = jwtSecret;
        this.jwtType = jwtType;
        this.setAuthenticationManager(authenticationManager);
        setFilterProcessesUrl("/api/login");
    }

    @Override
    protected final void successfulAuthentication(final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain chain, final Authentication authentication) throws IOException, ServletException {
                User user = (User) authentication.getPrincipal();
                SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
                String token = Jwts.builder()
                        .signWith(secretKey, SignatureAlgorithm.HS512)
                        .setHeaderParam("typ", jwtType)
                        .setIssuer(jwtIssuer)
                        .setAudience(jwtAudience)
                        .setSubject(user.getUsername())
                        .compact();
            
                response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    }
}
