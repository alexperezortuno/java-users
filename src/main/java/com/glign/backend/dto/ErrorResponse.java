package com.glign.backend.dto;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private int statusCode;
}
