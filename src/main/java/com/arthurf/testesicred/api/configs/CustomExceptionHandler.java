package com.arthurf.testesicred.api.configs;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import com.arthurf.testesicred.api.exceptions.BusinessException;

/**
 * Parse exceptions and return a proper response.
 */
@ControllerAdvice()
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Default message for internal server error.
     */
    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Something went wrong. Please try again later or contact the system administrator.";

    /**
     * Handle BusinessException.
     * 
     * @param e the BusinessException.
     * @return the response entity.
     */
    @ExceptionHandler({ BusinessException.class })
    public ResponseEntity<Object> handleGenericException(final BusinessException e) {
        return ResponseEntity.status(e.getStatusCode()).body(getResponseBody(e.getMessage()));
    }

    /**
     * Handle RuntimeException and Exception.
     * 
     * @param e the exception.
     * @return the response entity.
     */
    @ExceptionHandler({ RuntimeException.class, Exception.class })
    public ResponseEntity<Object> handleException(final Throwable e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(getResponseBody(INTERNAL_SERVER_ERROR_MESSAGE));
    }

    /**
     * Get the response body.
     * 
     * @param message the message.
     * @return the response body.
     */
    private String getResponseBody(final String message) {
        return String.format("{\"message\": \"%s\"}", message);
    }

}
