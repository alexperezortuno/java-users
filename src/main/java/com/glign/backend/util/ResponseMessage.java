package com.glign.backend.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResponseMessage<T> {
    private HttpStatus code;
    private T dto;
}
