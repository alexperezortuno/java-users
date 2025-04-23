package com.glign.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserFullResDto implements Serializable {
    private String name;
    private String email;
    private String password;
    private List<PhoneReqDto> phones;
    private String id;
    private String token;
    private boolean active;
    private Date created;
    private Date lastLogin;
    private Date lastModified;
}
