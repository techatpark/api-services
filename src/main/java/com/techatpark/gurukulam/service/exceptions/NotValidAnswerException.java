package com.techatpark.gurukulam.service.exceptions;

/**
 * The type Not valid answer exception.
 */
public class NotValidAnswerException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new Not valid answer exception.
     *
     * @param message   the message
     * @param throwable the throwable
     */
    public NotValidAnswerException(final String message,
                                   final Throwable throwable) {
        super(message, throwable);
    }
}
