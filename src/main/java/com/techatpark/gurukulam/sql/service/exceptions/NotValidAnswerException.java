package com.techatpark.gurukulam.sql.service.exceptions;

public class NotValidAnswerException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param throwable
     * @param message
     */
    public NotValidAnswerException(final String message,
                                   final Throwable throwable) {
        super(message, throwable);
    }
}
