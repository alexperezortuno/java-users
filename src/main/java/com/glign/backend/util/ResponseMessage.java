package com.glign.backend.util;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseMessage<T> {
    private HttpStatus code;
    private T dto;
}
