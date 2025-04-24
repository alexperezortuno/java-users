package com.glign.backend.dto;

import com.glign.backend.util.Constant;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LoginRequestDto implements Serializable {
    @NotBlank(message = Constant.USERNAME_IS_REQUIRED)
    private String username;
    @NotBlank(message = Constant.PASSWORD_REQUIRED)
    private String password;
}
