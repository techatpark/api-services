package com.techatpark.starter.security.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JwtAutherizationFilter extends BasicAuthenticationFilter {

    /**
     * logger for thiss class.
     */
    private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

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
     * 
     * @param jwtIssuer
     * @param jwtSecret
     * @param jwtAudience
     * @param jwtType *
     * @param authenticationManager
     */
    public JwtAutherizationFilter(final AuthenticationManager authenticationManager, final String jwtAudience,
            final String jwtIssuer, final String jwtSecret, final String jwtType) {
        super(authenticationManager);
        this.jwtAudience = jwtAudience;
        this.jwtIssuer = jwtIssuer;
        this.jwtSecret = jwtSecret;
        this.jwtType = jwtType;
    }

    private UsernamePasswordAuthenticationToken parseToken(final HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null && token.startsWith("Bearer ")) {
            String claims = token.replace("Bearer ", "");
            try {
                Jws<Claims> claimsJws = Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(claims);

                String username = claimsJws.getBody().getSubject();

                if ("".equals(username) || username == null) {
                    return null;
                }
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(authority);
                return new UsernamePasswordAuthenticationToken(username, null, null);
            } catch (JwtException exception) {
                logger.warn("Some exception : {} failed : {}", token, exception.getMessage());
            }
        }

        return null;
    }

    /** */
    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
            final FilterChain filterChain) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authentication = parseToken(request);

        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}