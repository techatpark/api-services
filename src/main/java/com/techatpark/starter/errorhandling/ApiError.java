package com.techatpark.starter.errorhandling;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

/**
 * Model class for application errors.
 */
public final class ApiError {
    /**
     * tells a http status.
     */
    private final HttpStatus status;
    /**
     * tells a message.
     */
    private final String message;
    /**
     * tells error.
     */
    private final List<String> errors;

    /**
     * Builds API Error.
     *
     * @param aStatus  the status
     * @param aMessage the message
     * @param theErrors  the errors
     */
    public ApiError(final HttpStatus aStatus, final String aMessage,
                    final List<String> theErrors) {
        this.status = aStatus;
        this.message = aMessage;
        this.errors = theErrors;
    }

    /**
     * Builds API from Single Error.
     *
     * @param badRequest       the bad request
     * @param localizedMessage the localized message
     * @param error            the error
     */
    public ApiError(final HttpStatus badRequest,
                    final String localizedMessage,
                    final String error) {
        this(badRequest, localizedMessage, Arrays.asList(error));
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets errors.
     *
     * @return the errors
     */
    public List<String> getErrors() {
        return errors;
    }
}
