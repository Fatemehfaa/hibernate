package com.example.hibernate.address;

import com.example.hibernate.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    Long id;

    String street;
    String city;
    @OneToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

    @CreationTimestamp
    Date creationAt;
    @UpdateTimestamp
    Date updateAt;
}
