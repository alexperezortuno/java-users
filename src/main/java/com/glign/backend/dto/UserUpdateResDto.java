package com.glign.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserUpdateResDto extends UserFullResDto {
    private String name;
    @JsonIgnore
    private Date created;
}
