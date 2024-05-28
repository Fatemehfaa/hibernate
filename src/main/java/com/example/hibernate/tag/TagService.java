package com.example.hibernate.tag;

import com.example.hibernate.user.UserMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

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


    public TagDto update(TagDto tagDto){
        TagEntity tag = tagRepository.findById(tagDto.getId()).orElseThrow();
        TagMapper.INSTANCE.updateToDto(tagDto , tag);
        return TagMapper.INSTANCE.toDto(tagRepository.save(tag));
    }

    public void deleteTagById(long id){
        tagRepository.deleteById(id);
    }


}
