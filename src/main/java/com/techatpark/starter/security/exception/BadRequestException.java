package com.techatpark.starter.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    /**
     * Basic.
     * @param message
     */
    public BadRequestException(final String message) {
        super(message);
    }

    /**
     * With another error.
     * @param message
     * @param cause
     */
    public BadRequestException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
