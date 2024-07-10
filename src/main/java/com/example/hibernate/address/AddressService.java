package com.example.hibernate.address;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import org.springframework.stereotype.Service;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@RequiredArgsConstructor
public class AddressService {
    AddressRepository addressRepository;
    RedissonClient redissonClient;

    public AddressDto saveAddress(AddressDto addressDto) {
        AddressEntity entity = AddressMapper.INSTANCE.toEntity(addressDto);
        AddressDto dto = AddressMapper.INSTANCE.toDto(addressRepository.save(entity));

        RMap<Long ,AddressDto> rMap = redissonClient.getMap("addresses");
        rMap.put(dto.getId(), dto);

        return dto;
    }

    public AddressDto findById(long id) {
        AddressDto dto = AddressMapper.INSTANCE.toDto(addressRepository.findById(id).orElseThrow());
        RMap<Long ,AddressDto> rMap = redissonClient.getMap("addresses");


    }

    public AddressDto updateAddress(AddressDto addressDto ) {
        AddressEntity address = addressRepository.findById(addressDto.getId()).orElseThrow();
        AddressMapper.INSTANCE.updateDto(addressDto , address);
        return AddressMapper.INSTANCE.toDto(addressRepository.save(address));

    }

    public void deleteById(long id) {
        addressRepository.deleteById(id);
    }



}
