package com.example.hibernate.user;

import com.example.hibernate.address.AddressDto;

import com.example.hibernate.address.AddressEntity;
import com.example.hibernate.address.AddressMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto toDto(UserEntity user);
    UserEntity toEntity(UserDto dto);

    void updateToDto(UserEntity userEntity ,@MappingTarget UserDto dto);

    default AddressDto addressDto(AddressEntity address) {
        return Mappers.getMapper(AddressMapper.class).toDto(address);
    }

}
