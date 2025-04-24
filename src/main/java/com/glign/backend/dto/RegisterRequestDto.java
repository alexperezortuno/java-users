package com.glign.backend.dto;

import com.glign.backend.util.Constant;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RegisterRequestDto extends LoginRequestDto implements Serializable {
    @NotBlank(message = Constant.NAME_IS_REQUIRED)
    private String name;
}
