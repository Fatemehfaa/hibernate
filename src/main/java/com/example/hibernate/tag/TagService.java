package com.example.hibernate.tag;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TagService {
    TagRepository tagRepository;
    RedissonClient redissonClient;
    RMap<Long, TagDto> rMap;

    public TagService(TagRepository tagRepository, RedissonClient redissonClient) {
        this.tagRepository = tagRepository;
        this.redissonClient = redissonClient;
        this.rMap = redissonClient.getMap("tags");
    }


    public TagDto saveTag(TagDto tag) {
        TagEntity entity = TagMapper.INSTANCE.toEntity(tag);
        TagDto dto = TagMapper.INSTANCE.toDto(tagRepository.save(entity));
        rMap.put(tag.getId(), dto);

        return dto;

    }

    public TagDto getTagById(long id) {
        TagDto tagDto = rMap.get(id);
        if (tagDto != null) {
            return tagDto;
        }
        TagDto dto = TagMapper.INSTANCE.toDto(tagRepository.findById(id).orElseThrow());
        rMap.put(id, dto);

        return dto;
    }


    public TagDto update(TagDto tagDto) {
        TagEntity tag = tagRepository.findById(tagDto.getId()).orElseThrow();
        TagMapper.INSTANCE.updateToDto(tagDto, tag);
        TagDto dto = TagMapper.INSTANCE.toDto(tagRepository.save(tag));
        rMap.put(tag.getId(), dto);

        return dto;
    }

    public void deleteTagById(long id) {
        tagRepository.deleteById(id);
        rMap.remove(id);
    }

    public List<TagDto> getAllTags() {
        if (!rMap.isEmpty()){
            return rMap.values().stream().toList();
        }
        List<TagDto> dtoList = TagMapper.INSTANCE.toDtoList(tagRepository.findAll());
        rMap.putAll(dtoList.stream().collect(Collectors.toMap(TagDto::getId, Function.identity())));

        return dtoList;
    }


}
