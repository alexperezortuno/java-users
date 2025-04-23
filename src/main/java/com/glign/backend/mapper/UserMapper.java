package com.glign.backend.mapper;

import com.glign.backend.dto.UserDto;
import com.glign.backend.dto.UserRequestDto;
import com.glign.backend.jpa.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User dtoToEntity(UserDto user);
    UserDto entityToDto(User user);

    User dtoToEntity(UserRequestDto userRequestDto);
}
