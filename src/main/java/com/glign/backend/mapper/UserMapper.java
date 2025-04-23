package com.glign.backend.mapper;

import com.glign.backend.dto.UserDto;
import com.glign.backend.dto.UserRequestDto;
import com.glign.backend.jpa.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User dtoToEntity(UserDto user);
    UserDto entityToDto(User user);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "phones", target = "phones")
    User dtoToEntity(UserRequestDto userRequestDto);
}
