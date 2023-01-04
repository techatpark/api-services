package com.gurukulams.web.starter.security.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Rest authentication entry point.
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * declares the logger.
     */
    private static final Logger LOGGER =
            LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

    /**
     * commence request.
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param e
     * @throws IOException
     */
    @Override
    public void commence(final HttpServletRequest httpServletRequest,
                         final HttpServletResponse httpServletResponse,
                         final AuthenticationException e)
            throws IOException {
        LOGGER.error("Responding with unauthorized error. Message - {}",
                e.getMessage());
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                e.getLocalizedMessage());
    }
}
