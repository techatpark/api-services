package com.example.demo;

import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.security.Keys;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    /**
     * summa .
     */
    private final String jwtAudience;
    /**
     * summa .
     */
    private final String jwtIssuer;
    /**
     * summa .
     */
    private final String jwtSecret;
    /**
     * summa .
     */
    private final String jwtType;

    /**
     * summa.
     * 
     * @param authenticationManager
     * @param jwtAudience
     * @param jwtIssuer
     * @param jwtSecret
     * @param jwtType
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

    /**
     * sss.
     */
    @Override
    protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response,
            final FilterChain filterChain, final Authentication authentication) {
        final Long mSecond = (long) 864000000;
        final User user = (User) authentication.getPrincipal();
        final SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        final String token = Jwts.builder().signWith(SignatureAlgorithm.HS512, secretKey).setHeaderParam("typ", jwtType)
                .setIssuer(jwtIssuer).setAudience(jwtAudience).setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + mSecond)).compact();

        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    }

    private UsernamePasswordAuthenticationToken parseToken(final HttpServletRequest request) {
        final String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null && token.startsWith("Bearer ")) {
            final String claims = token.replace("Bearer ", "");
            try {
                final Jws<Claims> claimsJws = Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(claims);

                final String username = claimsJws.getBody().getSubject();

                if ("".equals(username) || username == null) {
                    return null;
                }

                // TODO roles here!

                return new UsernamePasswordAuthenticationToken(username, null, null);
            } catch (final JwtException exception) {
                System.out.println("Some exception : {} failed : {}" + token + exception.getMessage());
                // log.warn("Some exception : {} failed : {}", token, exception.getMessage());
            }
        }

        return null;
    }

    /**
     * dddd.
     * 
     * @param request
     * @param response
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
            final FilterChain filterChain) throws IOException, ServletException {
        final UsernamePasswordAuthenticationToken authentication = parseToken(request);

        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }

}
