package com.example.hibernate.tag;

import com.example.hibernate.user.UserMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@RequiredArgsConstructor
public class TagService {
    TagRepository tagRepository;

    public TagDto saveTag(TagDto tag){
        TagEntity entity = TagMapper.INSTANCE.toEntity(tag);
       return TagMapper.INSTANCE.toDto( tagRepository.save(entity));

    }

    public TagDto getTagById(long id){
        return TagMapper.INSTANCE.toDto(tagRepository.findById(id).orElseThrow());
    }


}
