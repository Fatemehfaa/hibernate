package com.example.hibernate.post;

import com.example.hibernate.address.AddressDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/update")
    public ResponseEntity<PostDto> update(@RequestBody PostDto post) {
        return ResponseEntity.ok(postService.update(post));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
    postService.deleteById(id);
    }

    @GetMapping("/getAllPosts/")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }



}
