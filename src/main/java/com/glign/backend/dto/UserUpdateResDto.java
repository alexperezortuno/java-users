package com.glign.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserUpdateResDto extends UserFullResDto {
    private String name;
    @JsonIgnore
    private String email;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String token;
    @JsonIgnore
    private Date lastLogin;
    @JsonIgnore
    private Date created;
    @JsonIgnore
    private List<PhoneReqDto> phones;
}
