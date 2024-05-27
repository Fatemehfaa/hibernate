package com.example.hibernate.post;

import com.example.hibernate.BaseDto;
import com.example.hibernate.tag.TagDto;
import com.example.hibernate.user.UserDto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PostDto extends BaseDto {
    String title;
    UserDto user;
    List<TagDto> tags;

}
