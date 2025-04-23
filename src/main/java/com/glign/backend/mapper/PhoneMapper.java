package com.glign.backend.mapper;

import com.glign.backend.dto.PhoneDto;
import com.glign.backend.jpa.entity.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhoneMapper {
    PhoneMapper INSTANCE = Mappers.getMapper(PhoneMapper.class);

    @Mapping(target = "id", ignore = true)
    Phone dtoToEntity(PhoneDto dto);

    PhoneDto entityToDto(Phone entity);

    List<Phone> dtoToEntity(List<PhoneDto> list);
    List<PhoneDto> entityToDto(List<Phone> list);
}
