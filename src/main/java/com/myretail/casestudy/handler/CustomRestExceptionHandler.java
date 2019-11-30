package com.myretail.casestudy.handler;

import com.myretail.casestudy.exceptions.ProductNotFoundException;
import com.myretail.casestudy.exceptions.ProductRequestInvalidException;
import com.myretail.casestudy.exceptions.ProductServiceException;
import com.myretail.casestudy.model.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

/**
 * The Custom exception handler class
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Method to handle ProductRequestInvalidException
     *
     * @param ex ProductRequestInvalidException
     * @return error details
     */
    @ExceptionHandler(ProductRequestInvalidException.class)
    public ResponseEntity<Object> handleResponseStatusException(ProductRequestInvalidException ex) {
        log.error("Exception message: {}", ex.getMessage());
        log.error("Exception cause: {}", ex.getCause());
        return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    /**
     * Method to handle ProductNotFoundException
     *
     * @param ex ProductNotFoundException
     * @return error details
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex) {
        log.error("Exception message: {}", ex.getMessage());
        log.error("Exception cause: {}", ex.getCause());
        return buildResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage());
    }


    /**
     * Method to handle ProductServiceException
     *
     * @param ex ProductServiceException
     * @return error details
     */
    @ExceptionHandler(ProductServiceException.class)
    public ResponseEntity<Object> handleProductServiceException(ProductServiceException ex) {
        log.error("Exception message: {}", ex.getMessage());
        log.error("Exception cause: {}", ex.getCause());
        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    /**
     * Method to handle MethodArgumentNotValidException
     *
     * @param ex      MethodArgumentNotValidException
     * @param headers http headers
     * @param status  http status
     * @param request Web request
     * @return error details
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        log.error("request payload validation error due to exception: {}", ex.getMessage());
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return buildResponseEntity(HttpStatus.BAD_REQUEST, errorMessage);
    }

    /**
     * Method to construct ErrorMessage
     *
     * @param status  http status
     * @param message error message
     * @return error details
     */
    public ResponseEntity<Object> buildResponseEntity(HttpStatus status, String message) {
        ErrorMessage error = new ErrorMessage(status, message);
        return new ResponseEntity<>(error, error.getStatus());
    }
}
