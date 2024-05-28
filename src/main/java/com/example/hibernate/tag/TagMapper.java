package com.example.hibernate.tag;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);
    TagDto toDto(TagEntity tag) ;
    TagEntity toEntity(TagDto tagDto) ;
    void updateToDto(TagDto tagDto,@MappingTarget TagEntity tag) ;
}
