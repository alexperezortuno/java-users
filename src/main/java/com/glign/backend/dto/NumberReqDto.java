package com.glign.backend.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class NumberReqDto implements Serializable {
    @NotBlank(message = "number is required")
    private String number;
}
