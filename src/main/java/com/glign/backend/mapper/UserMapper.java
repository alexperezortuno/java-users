package com.glign.backend.mapper;

import com.glign.backend.dto.*;
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
    User dtoToEntity(UserCreateRequestDto userCreateRequestDto);

    @Mapping(target = "id", ignore = true)
    User dtoToEntity(UserFullResDto user);

    UserFullResDto entityToDto(User user);

    List<Phone> toPhoneEntityList(List<PhoneDto> list);
    List<PhoneDto> toPhoneDtoList(List<Phone> list);

    @Mapping(target = "id", source = "uuid")
    @Mapping(target = "created", expression = "java(user.getCreatedDate())")
    @Mapping(target = "lastModified", source = "lastModifiedDate")
    UserFullResDto reduceEntityToDto(User user);

    @Mapping(target = "id", source = "uuid")
    @Mapping(target = "lastModified", source = "lastModifiedDate")
    UserUpdateResDto reduceEntityToUpdateDto(User user);
}
