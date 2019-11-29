package com.myretail.casestudy.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * The pojo class ErrorMessage
 */
@Getter
@Setter
public class ErrorMessage {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;

    /**
     * Default constructor
     */
    public ErrorMessage() {
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Parameterised constructor
     *
     * @param status  HTTP status
     * @param message error message
     */
    public ErrorMessage(HttpStatus status, String message) {
        this();
        this.status = status;
        this.message = message;
    }
}
