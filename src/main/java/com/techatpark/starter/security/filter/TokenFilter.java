package com.techatpark.starter.security.filter;
//This filter is used to create toke with given username and password.

import com.techatpark.starter.security.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class TokenFilter extends OncePerRequestFilter {

    private final TokenUtil tokenUtil;


    Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    public TokenFilter(TokenUtil tokenUtil) {
        this.tokenUtil = tokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        if (authorizationHeader != null) {
            String token = authorizationHeader.split(" ")[1];
            Claims claims = tokenUtil.parseToken(token);
            logger.info("Token Obtained for : {} is {}", httpServletRequest.getRequestURI(), token);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                    claims.getSubject(), null, List.of()));
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
