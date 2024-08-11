package com.example.hibernate.user;

import com.example.hibernate.address.AddressEntity;
import com.example.hibernate.post.PostEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class UserEntity {
    @Id
    @GeneratedValue
    Long id;

    String name;
    String password;


    @OneToOne(mappedBy = "user")
    AddressEntity address;

    @OneToMany(mappedBy = "user")
    List<PostEntity> posts;

    @CreationTimestamp
    Date creationAt;
    @UpdateTimestamp
    Date updateAt;
}


