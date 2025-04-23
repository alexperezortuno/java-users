package com.glign.backend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRequestDto {
    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "email is required")
    @Email(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "El formato del correo no es válido")
    private String email;

    @NotBlank(message = "password is required")
    @Pattern(regexp = "${password.regex}", message = "El formato de la contraseña no es válido")
    private String password;

    @Valid
    private List<PhoneDto> phones;
}
