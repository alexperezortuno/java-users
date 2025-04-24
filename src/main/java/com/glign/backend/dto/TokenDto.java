package com.glign.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TokenDto implements Serializable {
    private String token;

    public TokenDto(String token) {
        this.token = token;
    }
}
