package com.glign.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SimpleResponse implements Serializable {
    private String message;

    public SimpleResponse(String message) {
        this.message = message;
    }
}
