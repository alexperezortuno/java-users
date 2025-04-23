package com.glign.backend.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private String name;
    private String email;
    private String password;
    private List<PhoneDto> phones;
    private Date lastLogin;
    private String token;
    private boolean isActive;
}
