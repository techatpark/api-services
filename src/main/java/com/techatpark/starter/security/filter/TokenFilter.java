package com.techatpark.starter.security.filter;

import com.techatpark.starter.security.utils.TokenUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenFilter extends OncePerRequestFilter {

    /**
     * declare a TokenUtil.
     */
    private final TokenUtil tokenUtil;

    /**
     * @param aTokenUtil
     */
    public TokenFilter(final TokenUtil aTokenUtil) {
        this.tokenUtil = aTokenUtil;
    }

    /**
     * @param httpServletRequest
     * @param httpServletResponse
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(
            final HttpServletRequest httpServletRequest,
            final HttpServletResponse httpServletResponse,
            final FilterChain filterChain) throws ServletException,
            IOException {
        String authorizationHeader =
                httpServletRequest.getHeader("Authorization");

        if (authorizationHeader != null) {
            String token = authorizationHeader.split(" ")[1];
            SecurityContextHolder.getContext()
                    .setAuthentication(tokenUtil.getAuthentication(token));
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
