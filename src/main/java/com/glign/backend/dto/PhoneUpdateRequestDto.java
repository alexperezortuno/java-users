package com.glign.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PhoneUpdateRequestDto {
    private List<PhoneDto> phones;
}
