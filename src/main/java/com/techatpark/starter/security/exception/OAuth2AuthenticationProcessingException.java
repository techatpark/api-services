package com.techatpark.starter.security.exception;

import org.springframework.security.core.AuthenticationException;

public class OAuth2AuthenticationProcessingException
        extends AuthenticationException {
    /**
     * declare a constructor.
     * @param msg
     * @param t
     */
    public OAuth2AuthenticationProcessingException(final String msg,
                                                   final Throwable t) {
        super(msg, t);
    }

    /**
     * @param msg
     */
    public OAuth2AuthenticationProcessingException(final String msg) {
        super(msg);
    }
}
