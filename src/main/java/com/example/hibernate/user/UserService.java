package com.example.hibernate.user;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@RequiredArgsConstructor
public class UserService {
    UserRepository userRepository;

    public UserDto getUserByName(String name) {
        return UserMapper.INSTANCE.toDto(userRepository.findByName(name).orElseThrow());
    }


    public UserDto saveUser(UserDto userDto) {
        return UserMapper.INSTANCE.toDto(userRepository.save(UserMapper.INSTANCE.toEntity(userDto)));
    }


    public UserDto getUserById(Long id) {
        return UserMapper.INSTANCE.toDto(userRepository.findById(id).orElseThrow());
    }




}
