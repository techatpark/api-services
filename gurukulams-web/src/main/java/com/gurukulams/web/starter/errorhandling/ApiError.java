package com.gurukulams.web.starter.errorhandling;


import java.util.Arrays;
import java.util.List;

/**
 * Model class for application errors.
 */
public final class ApiError {

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
     * @param aMessage  the message
     * @param theErrors the errors
     */
    public ApiError(final String aMessage,
                    final List<String> theErrors) {
        this.message = aMessage;
        this.errors = theErrors;
    }

    /**
     * Builds API from Single Error.
     *
     * @param localizedMessage the localized message
     * @param error            the error
     */
    public ApiError(final String localizedMessage,
                    final String error) {
        this(localizedMessage, Arrays.asList(error));
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
