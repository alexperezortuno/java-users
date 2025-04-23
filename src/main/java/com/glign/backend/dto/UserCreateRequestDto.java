package com.glign.backend.dto;

import com.glign.backend.component.PasswordPattern;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserCreateRequestDto {
    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "email is required")
    @Email(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "the email format is not valid")
    private String email;

    @NotBlank(message = "password is required")
    @PasswordPattern
    private String password;

    @Valid
    private List<PhoneDto> phones;
}
