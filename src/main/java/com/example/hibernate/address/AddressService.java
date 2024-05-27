package com.example.hibernate.address;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@RequiredArgsConstructor
public class AddressService {
    AddressRepository addressRepository;

    public AddressDto saveAddress(AddressDto addressDto) {
        AddressEntity entity = AddressMapper.INSTANCE.toEntity(addressDto);
        return AddressMapper.INSTANCE.toDto(addressRepository.save(entity));
    }


    public AddressDto findById(long id) {
        return AddressMapper.INSTANCE.toDto(addressRepository.findById(id).orElseThrow());
    }

}
