package com.example.hibernate.user;

import com.example.hibernate.BaseDto;
import com.example.hibernate.address.AddressDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserDto extends BaseDto {
    String name;
    String password;
    AddressDto address;

}
