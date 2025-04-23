package com.glign.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneDto {
    @NotBlank(message = "number is required")
    private String number;

    @NotBlank(message = "city code is required")
    private String cityCode;

    @NotBlank(message = "country code is required")
    private String countryCode;
}
