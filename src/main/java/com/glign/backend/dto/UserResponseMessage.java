package com.glign.backend.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class UserResponseMessage {
    private UserDto user;
    private HttpStatus status;

    public UserResponseMessage(UserDto user, HttpStatus status) {
        this.user = user;
        this.status = status;
    }
}
