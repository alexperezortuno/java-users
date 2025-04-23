package com.glign.backend.util;

import org.springframework.http.ResponseEntity;

public final class UserHttpResponseBuilder {
    private UserHttpResponseBuilder(){}

    public static ResponseEntity<?> buildResponse(ResponseMessage<?> data) {
        return new ResponseEntity<>(data.getDto(), data.getCode());
    }
}
