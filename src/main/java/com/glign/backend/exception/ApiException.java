package com.glign.backend.exception;

import com.glign.backend.util.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiException extends Exception {
    private final HttpStatus status;

    public ApiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public ResponseEntity<ErrorMessage> getResponse() {
        var error = new ErrorMessage(this.getMessage());
        return new ResponseEntity<>(error, this.status);
    }
}