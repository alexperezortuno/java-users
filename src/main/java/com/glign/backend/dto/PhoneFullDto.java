package com.glign.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PhoneFullDto implements Serializable {
    private String number;
    private String cityCode;
    private String countryCode;
}
