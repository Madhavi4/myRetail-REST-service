package com.myretail.casestudy.exceptions;

/**
 * The custom Exception class ProductRequestInvalidException
 */
public class ProductRequestInvalidException extends ProductServiceException{

    public ProductRequestInvalidException(String message) {
        super(message);
    }
}
