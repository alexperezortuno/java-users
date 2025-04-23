package com.glign.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserUpdateReqDto implements Serializable {
    @NotBlank(message = "name is required")
    private String name;

    @NotNull(message = "active is required")
    private boolean active;
}
