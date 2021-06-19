package com.techatpark.starter.errorhandling;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

/**
 * Model class for application errors.
 */
public final class ApiError {

    private final HttpStatus status;
    private final String message;
    private final List<String> errors;

    /**
     * Builds API Error.
     *
     * @param status
     * @param message
     * @param errors
     */
    public ApiError(HttpStatus status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    /**
     * Builds API from Single Error
     *
     * @param badRequest
     * @param localizedMessage
     * @param error
     */
    public ApiError(HttpStatus badRequest, String localizedMessage, String error) {
        this(badRequest, localizedMessage, Arrays.asList(error));
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }
}
