package com.glign.backend.util;

import org.springframework.http.ResponseEntity;

public final class HttpResponseBuilder {
    private HttpResponseBuilder(){}

    public static ResponseEntity<?> buildResponse(ResponseMessage<?> data) {
        return new ResponseEntity<>(data.getDto(), data.getCode());
    }
}
