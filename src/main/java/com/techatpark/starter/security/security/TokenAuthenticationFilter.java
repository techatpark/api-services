package com.techatpark.starter.security.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
    /**
     * N.
     */
    static final int N = 7;
    /**
     * logger.
     */
    private static final Logger LOG =
            LoggerFactory.getLogger(TokenAuthenticationFilter.class);
    /**
     * tokenProvider.
     */
    @Autowired
    private TokenProvider tokenProvider;
    /**
     * customUserDetailsService.
     */
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /**
     * override method to.
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                String userName = tokenProvider.getUserNameFromToken(jwt);

                UserDetails userDetails =
                        customUserDetailsService.loadUserByUsername(userName);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null,
                                userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(request));

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }
        } catch (Exception ex) {
            LOG.error(
                    "Could not set user authentication in security context",
                    ex);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(final HttpServletRequest request) {
        var bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken)
                && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(N, bearerToken.length());
        }
        return null;
    }
}
