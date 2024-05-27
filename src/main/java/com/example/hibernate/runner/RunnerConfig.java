package com.example.hibernate.runner;

import com.example.hibernate.address.AddressDto;
import com.example.hibernate.address.AddressEntity;
import com.example.hibernate.address.AddressMapper;
import com.example.hibernate.address.AddressRepository;
import com.example.hibernate.post.PostEntity;
import com.example.hibernate.post.PostRepository;
import com.example.hibernate.tag.TagEntity;
import com.example.hibernate.tag.TagRepository;
import com.example.hibernate.user.UserEntity;
import com.example.hibernate.user.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)

public class RunnerConfig implements CommandLineRunner {
   UserRepository userRepository ;
   AddressRepository addressRepository ;
   PostRepository postRepository ;
   TagRepository tagRepository;

    @Override
    public void run(String... args) throws Exception {
        UserEntity user = userRepository.save(UserEntity.builder()
                .name("jadi")
                .password("0000")
                .build());

        AddressEntity address = addressRepository.save(AddressEntity.builder()
                .city("tehran")
                .street("mirdamad")
                .user(user)
                .build());

        List<TagEntity> tags = tagRepository.saveAll(List.of(
                TagEntity.builder()
                        .name("jahan")
                        .build(),
                TagEntity.builder()
                        .name("jeyran")
                        .build())

        );

        System.out.println(tags);
//
//        PostEntity post = postRepository.save(PostEntity.builder()
//                .title("art")
//                .tags(tags)
//                .user(user)
//                .build());


    }
}
