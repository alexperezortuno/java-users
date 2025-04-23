package com.glign.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserFullResDto {
    @JsonIgnore
    private String name;
    @JsonIgnore
    private String email;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private List<PhoneDto> phones;
    private String id;
    private String token;
    private boolean active;
    private Date created;
    private Date lastLogin;
    private Date lastModified;
}
