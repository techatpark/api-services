package com.techatpark.starter.security.filter;
//This filter is used to create toke with given username and password.

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

public class TokenFilter extends OncePerRequestFilter {

    Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        if (authorizationHeader != null) {
            String token = new String(Base64.getDecoder().decode(authorizationHeader.split(" ")[1]));
            String[] split = token.split(":");
            logger.info("Token Obtained for : {} is {}", httpServletRequest.getRequestURI(), token);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                    split[0], split[1]));
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
