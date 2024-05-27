package com.example.hibernate.post;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal= true)
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    PostService postService;

    @PostMapping("/savePost")
    public ResponseEntity<PostDto> savePost(@RequestBody PostEntity post) {
        return ResponseEntity.ok(postService.savePost(post));
    }

    @GetMapping("/getPostById/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }


}
