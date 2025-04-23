package com.glign.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserResponseDto {
    private String id;
    private String token;
    private boolean active;
    private Date created;
    private Date lastLogin;
    private Date lastModified;
}
