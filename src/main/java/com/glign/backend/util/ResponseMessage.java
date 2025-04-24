package com.glign.backend.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResponseMessage<T> {
    private HttpStatus code;
    private T dto;

    public ResponseMessage(T dto, HttpStatus code) {
        this.code = code;
        this.dto = dto;
    }

    public ResponseMessage(T dto, int code) {
        this.code = HttpStatus.valueOf(code);
        this.dto = dto;
    }
}
