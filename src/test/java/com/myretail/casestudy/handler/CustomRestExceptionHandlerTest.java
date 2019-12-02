package com.myretail.casestudy.handler;

import com.myretail.casestudy.model.ErrorMessage;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CustomRestExceptionHandlerTest {

    private CustomRestExceptionHandler exceptionHandler;

    @Before
    public void setUp(){
        exceptionHandler = new CustomRestExceptionHandler();
    }

    @Test
    public void testBuildResponseEntity(){
        ResponseEntity<Object> response = exceptionHandler.buildResponseEntity(HttpStatus.NOT_FOUND, "test not Found");
        ErrorMessage error = (ErrorMessage) response.getBody();

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        assertEquals(error.getStatus(), HttpStatus.NOT_FOUND);
        assertEquals(error.getMessage(), "test not Found");
        assertNotNull(error.getTimestamp());
    }
}