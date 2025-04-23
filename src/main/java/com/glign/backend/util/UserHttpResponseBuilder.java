package com.glign.backend.util;

import com.glign.backend.dto.UserResponseMessage;
import org.springframework.http.ResponseEntity;

public final class UserHttpResponseBuilder {
    private UserHttpResponseBuilder(){}

    public static ResponseEntity<?> buildResponse(UserResponseMessage data) {
        return new ResponseEntity<>(data.getUser(), data.getStatus());
    }
}
