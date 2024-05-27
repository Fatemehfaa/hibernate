package com.example.hibernate.post;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);
    PostDto toDto(PostEntity post);
    PostEntity toEntity(PostDto dto);
    void updateToDto(PostDto postDto,@MappingTarget PostEntity post);

}
