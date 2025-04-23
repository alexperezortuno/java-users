package com.glign.backend.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UserResponseMessage {
    private UserDto user;
    private HttpStatus status;

    public UserResponseMessage(UserDto user, HttpStatus status) {
        this.user = user;
        this.status = status;
    }
}
