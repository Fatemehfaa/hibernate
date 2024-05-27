package com.example.hibernate.tag;

import com.example.hibernate.BaseDto;
import com.example.hibernate.post.PostDto;
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
public class TagDto extends BaseDto {
    String name;

}
