package com.arthurf.testesicred.api.exceptions;

import org.springframework.http.HttpStatus;

/**
 * BusinessException
 * 
 * Exception to be thrown when a business rule is violated.
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final String DEFAULT_MESSAGE = "Business rule violated.";
    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    private String message = DEFAULT_MESSAGE;

    /**
     * Constructor
     * 
     * @param message The error description
     * @param status  The HTTP status code
     * 
     */
    public BusinessException(final String message, final HttpStatus status) {
        super();
        this.message = message;
        this.status = status;
    }

    /**
     * @return The error description
     */
    @Override
    public String getMessage() {
        return this.message;
    }

    /**
     * @return The HTTP status code
     */
    public HttpStatus getStatusCode() {
        return this.status;
    }
}
