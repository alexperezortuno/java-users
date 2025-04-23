package com.glign.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequestDto {
    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "status is required")
    private boolean isActive;
}
