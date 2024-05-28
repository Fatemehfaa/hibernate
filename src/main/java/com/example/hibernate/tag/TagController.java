package com.example.hibernate.tag;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/tag")

public class TagController {
     TagService tagService;

     @PostMapping("/saveTag")
     public ResponseEntity<TagDto> saveTag(@RequestBody TagDto tagDto) {
         return new ResponseEntity<>(tagService.saveTag(tagDto),HttpStatus.CREATED);
     }

     @GetMapping("/getTagById/{id}")
     public ResponseEntity<TagDto> getTagById(@PathVariable Long id) {
         return ResponseEntity.ok(tagService.getTagById(id));
     }


     @PutMapping("/update")
    public ResponseEntity<TagDto> updateTag(@RequestBody TagDto tagDto) {
         return ResponseEntity.ok( tagService.update(tagDto));
     }

     @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
         tagService.deleteTagById(id);
     }


}
