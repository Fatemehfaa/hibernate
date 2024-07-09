package com.example.hibernate.post;

import com.example.hibernate.tag.TagEntity;
import com.example.hibernate.user.UserEntity;
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

public class PostEntity {
    @Id
    @GeneratedValue
    Long id;
    String title;

    @ManyToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name = "user_id")
    UserEntity user;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name= "post_tag"
    ,joinColumns = @JoinColumn(name = "post_id")
    ,inverseJoinColumns = @JoinColumn(name = "tag_id"))
    List<TagEntity> tags;

    @CreationTimestamp
    Date creationAt;
    @UpdateTimestamp
    Date updateAt;


}
