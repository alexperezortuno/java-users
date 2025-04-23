package com.glign.backend.mapper;

import com.glign.backend.dto.PhoneDto;
import com.glign.backend.dto.UserDto;
import com.glign.backend.dto.UserRequestDto;
import com.glign.backend.jpa.entity.Phone;
import com.glign.backend.jpa.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "createdDate", expression = "java(new java.util.Date())")
    User dtoToEntity(UserRequestDto userRequestDto);

    User dtoToEntity(UserDto user);

    UserDto entityToDto(User user);

    List<Phone> toPhoneEntityList(List<PhoneDto> list);
    List<PhoneDto> toPhoneDtoList(List<Phone> list);
}
