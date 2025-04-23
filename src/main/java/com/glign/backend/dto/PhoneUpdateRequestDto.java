package com.glign.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class PhoneUpdateRequestDto implements Serializable {
    private List<PhoneReqDto> phones;
}
