package com.glign.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PhoneReqDto implements Serializable {
    @NotBlank(message = "number is required")
    private String number;

    @NotBlank(message = "city code is required")
    private String cityCode;

    @NotBlank(message = "country code is required")
    private String countryCode;
}
