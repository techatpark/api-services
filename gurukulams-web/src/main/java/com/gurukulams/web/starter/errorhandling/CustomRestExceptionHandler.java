package com.gurukulams.web.starter.errorhandling;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Custom rest exception handler.
 */
@ControllerAdvice
public final class CustomRestExceptionHandler
        extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException ex,
            final HttpHeaders headers,
            final HttpStatus status, final WebRequest request) {
        final List<String> errors = new ArrayList<String>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(
                    error.getField() + ": " + error.getDefaultMessage());
        }
        final ApiError apiError = new ApiError("Validation Failed", errors);
        return handleExceptionInternal(ex, apiError, headers,
                HttpStatus.BAD_REQUEST, request);
    }

    /**
     * @param exception
     * @param request
     * @return ResponseEntity
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(
            final ConstraintViolationException exception,
            final WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        final List<String> errors = new ArrayList();
        for (final ConstraintViolation<?> error
                : exception.getConstraintViolations()) {
            errors.add(
                    error.getPropertyPath() + ": " + error.getMessage());
        }
        final ApiError apiError = new ApiError("Validation Failed", errors);
        return handleExceptionInternal(exception, apiError, headers,
                HttpStatus.BAD_REQUEST, request);
    }

    /**
     * @param exception
     * @param request
     * @return ResponseEntity
     */
    @ExceptionHandler(value = DuplicateKeyException.class)
    public ResponseEntity<Object> handleDuplicateKeyException(
            final DuplicateKeyException exception,
            final WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        final List<String> errors = new ArrayList();
        errors.add(
                exception.getMessage());

        final ApiError apiError = new ApiError("Validation Failed",
                errors);
        return handleExceptionInternal(exception, apiError, headers,
                HttpStatus.NOT_ACCEPTABLE, request);
    }

}
