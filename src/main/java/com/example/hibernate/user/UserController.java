package com.example.hibernate.user;

import com.example.hibernate.tag.TagEntity;
import jakarta.persistence.PostLoad;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@RequestMapping("/user")
public class UserController {
    UserService userService;

    @GetMapping("/getUserByName/{name}")
    public ResponseEntity<UserDto> getUserByName(@PathVariable String name){
        return new ResponseEntity<>(userService.getUserByName(name), HttpStatus.OK);
    }

    @PostMapping("/saveUser")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto user){
        return new ResponseEntity<>(userService.saveUser(user),HttpStatus.CREATED);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }


    @PutMapping("/update")
    public ResponseEntity<UserDto> update(@RequestBody UserDto user){
        return ResponseEntity.ok(userService.update(user));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserDto> delete(@PathVariable long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
