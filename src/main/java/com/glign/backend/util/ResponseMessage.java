package com.glign.backend.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResponseMessage<T> {
    private HttpStatus code;
    private T dto;

    public ResponseMessage(T dto) {
        if (dto instanceof ResponseCode) {
            this.code = HttpStatus.valueOf(((ResponseCode) dto).getCode());
        } else {
            this.code = HttpStatus.OK;
        }
        this.dto = dto;
    }

    public ResponseMessage(T dto, HttpStatus code) {
        this.code = code;
        this.dto = dto;
    }
}
