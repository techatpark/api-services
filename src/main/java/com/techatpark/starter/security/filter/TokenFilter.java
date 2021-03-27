package com.techatpark.starter.security.filter;

import com.techatpark.starter.security.utils.TokenUtil;
import io.jsonwebtoken.Claims;
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

public class TokenFilter extends OncePerRequestFilter {


    Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    private final TokenUtil tokenUtil;

    public TokenFilter(TokenUtil tokenUtil) {
        this.tokenUtil = tokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        if (authorizationHeader != null) {
            String token = authorizationHeader.split(" ")[1];
            logger.info("Token Obtained for : {} is {}", httpServletRequest.getRequestURI(), token);
            SecurityContextHolder.getContext().setAuthentication(tokenUtil.getAuthentication(token));
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
