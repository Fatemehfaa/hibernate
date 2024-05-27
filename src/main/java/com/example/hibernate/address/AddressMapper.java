package com.example.hibernate.address;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
    AddressDto toDto(AddressEntity address);
    AddressEntity toEntity(AddressDto dto);

    void updateDto(AddressDto dto, @MappingTarget AddressEntity address);
}
