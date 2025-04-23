package com.glign.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserUpdateFullReqDto extends UserUpdateReqDto {
    @NotBlank(message = "phones are required")
    private List<PhoneDto> phones;
    @NotBlank(message = "email is required")

    private String email;
    @NotBlank(message = "password is required")
    private String password;
    private String token;
}
