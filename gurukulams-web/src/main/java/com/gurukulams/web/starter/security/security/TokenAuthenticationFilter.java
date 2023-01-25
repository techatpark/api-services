package com.gurukulams.web.starter.security.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * The type Token authentication filter.
 */
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
    private final TokenProvider tokenProvider;
    /**
     * customUserDetailsService.
     */
    private final CustomUserDetailsService customUserDetailsService;

    /**
     * TokenAuthenticationFilter.
     *
     * @param aTokenProvider            token provider
     * @param aCustomUserDetailsService customer user detail service
     */
    public TokenAuthenticationFilter(final TokenProvider aTokenProvider,
                                     final CustomUserDetailsService
                                             aCustomUserDetailsService) {
        this.tokenProvider = aTokenProvider;
        this.customUserDetailsService = aCustomUserDetailsService;
    }

    /**
     * override method to.
     *
     * @param request     request
     * @param response    response
     * @param filterChain filterchain
     */
    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain)
            throws ServletException, IOException {
        try {
            final String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt)
                    && tokenProvider.validateToken(request, jwt)) {
                final String userName =
                        tokenProvider.getUserNameFromToken(request, jwt);

                final UserDetails userDetails =
                        customUserDetailsService.loadUserByUsername(userName);
                final UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null,
                                userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(request));

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }
        } catch (final Exception ex) {
            LOG.error(
                    "Could not set user authentication in security context",
                    ex);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(final HttpServletRequest request) {
        final var bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken)
                && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(N);
        }
        return null;
    }
}
