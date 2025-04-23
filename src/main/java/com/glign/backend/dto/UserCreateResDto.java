package com.glign.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * UserCreateResDto is a data transfer object that represents the response
 * after creating a user. It extends UserFullResDto and includes additional
 * fields for the user's name, email, password, and token.
 */
@Getter
@Setter
public class UserCreateResDto extends UserFullResDto implements Serializable {
    @JsonIgnore
    private String name;
    @JsonIgnore
    private String email;
    @JsonIgnore
    private String password;
    private String token;
    @JsonIgnore
    private List<PhoneReqDto> phones;
    @JsonIgnore
    private Date lastLogin;
}
