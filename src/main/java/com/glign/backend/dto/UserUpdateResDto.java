package com.glign.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserUpdateResDto {
    private String id;
    private String name;
    private boolean active;
    private Date lastModified;
}
