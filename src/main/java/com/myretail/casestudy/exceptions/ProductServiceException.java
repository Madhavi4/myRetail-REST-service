package com.myretail.casestudy.exceptions;

public class ProductServiceException extends Exception {

    public ProductServiceException(String message) {
        super(message);
    }

    public ProductServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
