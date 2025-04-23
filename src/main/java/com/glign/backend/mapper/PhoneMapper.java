package com.glign.backend.mapper;

import com.glign.backend.dto.PhoneReqDto;
import com.glign.backend.jpa.entity.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhoneMapper {
    PhoneMapper INSTANCE = Mappers.getMapper(PhoneMapper.class);

    @Mapping(target = "id", ignore = true)
    Phone dtoToEntity(PhoneReqDto dto);

    PhoneReqDto entityToDto(Phone entity);

    List<Phone> dtoToEntity(List<PhoneReqDto> list);
    List<PhoneReqDto> entityToDto(List<Phone> list);
}
