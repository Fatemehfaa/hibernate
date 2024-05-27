package com.example.hibernate.post;

import com.example.hibernate.tag.TagEntity;
import com.example.hibernate.tag.TagRepository;
import com.example.hibernate.user.UserEntity;
import com.example.hibernate.user.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@RequiredArgsConstructor
public class PostService {
    PostRepository postRepository;

    public PostDto savePost(PostEntity post) {
        return PostMapper.INSTANCE.toDto( postRepository.save(post));
    }

    public PostDto getPostById(Long id) {
        PostEntity postEntity = postRepository.findById(id).orElseThrow();
        return PostMapper.INSTANCE.toDto(postEntity);
    }

    public PostDto update(PostDto postDto){
        PostEntity post = postRepository.findById(postDto.getId()).orElseThrow();
        PostMapper.INSTANCE.updateToDto(postDto , post);
        return PostMapper.INSTANCE.toDto(postRepository.save(post));
    }

}
