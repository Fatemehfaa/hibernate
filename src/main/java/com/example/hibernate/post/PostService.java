package com.example.hibernate.post;

import com.example.hibernate.tag.TagEntity;
import com.example.hibernate.tag.TagRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@RequiredArgsConstructor
public class PostService {
    PostRepository postRepository;
    TagRepository tagRepository;

    public PostDto savePost(PostEntity post) {
        List<TagEntity> tags = new ArrayList<>();
        for (TagEntity tag : post.getTags()) {
            tagRepository.findById(tag.getId()).ifPresent(tags::add);
        }
        post.setTags(tags);
        return PostMapper.INSTANCE.toDto( postRepository.save(post));
    }

    public PostDto getPostById(Long id) {
        PostEntity postEntity = postRepository.findById(id).orElseThrow();
        return PostMapper.INSTANCE.toDto(postEntity);
    }

}
