package com.myretail.casestudy.exceptions;

/**
 * The custom Exception class ProductNotFoundException
 */
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
