package com.example.hibernate.post;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class PostService {
    PostRepository postRepository;
    RedissonClient redissonClient;
    RMap<Long, PostDto> rMap;

    public PostService(PostRepository postRepository, RedissonClient redissonClient) {
        this.postRepository = postRepository;
        this.redissonClient = redissonClient;
        this.rMap =redissonClient.getMap("posts");
    }

    public PostDto savePost(PostEntity post) {
        PostDto dto = PostMapper.INSTANCE.toDto(postRepository.save(post));

        rMap.put(dto.getId(), dto);

        return dto;
    }

    public PostDto getPostById(Long id) {
        PostDto postDto = rMap.get(id);
        if (postDto != null){
            return postDto;
        }

        PostEntity postEntity = postRepository.findById(id).orElseThrow();
        PostDto dto = PostMapper.INSTANCE.toDto(postEntity);
        rMap.put(id, dto);

        return dto;
    }

    public PostDto update(PostDto postDto){
        PostEntity post = postRepository.findById(postDto.getId()).orElseThrow();
        PostMapper.INSTANCE.updateToDto(postDto , post);
        PostDto dto = PostMapper.INSTANCE.toDto(postRepository.save(post));
        rMap.put(dto.getId(), dto);
        return dto;
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
        rMap.remove(id);
    }

    public List<PostDto> getAllPosts() {
        if (!rMap.isEmpty()) {
            return rMap.values().stream().toList();
        }
        List<PostDto> allPosts = PostMapper.INSTANCE.findAllPosts(postRepository.findAll());
        rMap.putAll(allPosts.stream().collect(Collectors.toMap(PostDto::getId , Function.identity())));

        return allPosts;
    }

}
