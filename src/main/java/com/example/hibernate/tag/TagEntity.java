package com.example.hibernate.tag;

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
@ToString
@Builder

public class TagEntity {
    @Id
    @GeneratedValue
    Long id;

    String name;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy ="tags")
    List<PostEntity> posts;

    @CreationTimestamp
    Date creationAt;
    @UpdateTimestamp
    Date updateAt;
}
