package com.glign.backend.exception;

import com.glign.backend.dto.ErrorResponse;
import java.util.ArrayList;
import java.util.List;

public class ApiException extends Exception {
    private final List<ErrorResponse> errorResponses = new ArrayList<>();

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public void addErrorResponse(ErrorResponse errorResponse) {
        this.errorResponses.add(errorResponse);
    }

    public List<ErrorResponse> getErrorResponses() {
        return errorResponses;
    }
}