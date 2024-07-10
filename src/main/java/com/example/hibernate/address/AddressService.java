package com.example.hibernate.address;

import com.example.hibernate.BaseDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class AddressService {
    AddressRepository addressRepository;
    RedissonClient redissonClient;
    RMap<Long ,AddressDto> rMap;

    public AddressService(AddressRepository addressRepository, RedissonClient redissonClient) {
        this.addressRepository = addressRepository;
        this.redissonClient = redissonClient;
        this.rMap =redissonClient.getMap("addresses");
    }


    public AddressDto saveAddress(AddressDto addressDto) {
        AddressEntity entity = AddressMapper.INSTANCE.toEntity(addressDto);
        AddressDto dto = AddressMapper.INSTANCE.toDto(addressRepository.save(entity));

        rMap.put(dto.getId(), dto);

        return dto;
    }

    public AddressDto findById(long id) {
        AddressDto addressDto = rMap.get(id);
        if (addressDto != null){
           return addressDto;
        }
        AddressDto dto = AddressMapper.INSTANCE.toDto(addressRepository.findById(id).orElseThrow());
        rMap.put(id, dto);
        return dto;
    }

    public AddressDto updateAddress(AddressDto addressDto ) {
        AddressEntity address = addressRepository.findById(addressDto.getId()).orElseThrow();
        AddressMapper.INSTANCE.updateDto(addressDto , address);
        AddressDto updateDto = AddressMapper.INSTANCE.toDto(addressRepository.save(address));
        rMap.put(updateDto.getId(), updateDto);
        return updateDto;

    }

    public void deleteById(long id) {
        addressRepository.deleteById(id);
        rMap.remove(id);
    }




    public List<AddressDto> findAllAddress(){
        if(!rMap.isEmpty()){
            return rMap.values().stream().toList();
        }
        List<AddressDto> all = AddressMapper.INSTANCE.mapAllToDto(addressRepository.findAll());

        Function<AddressDto,Long> longFunction = BaseDto::getId;
        Function<AddressDto,AddressDto> dtoFunction = AddressDto -> AddressDto;

        rMap.putAll(all.stream()
                .collect(Collectors.toMap(BaseDto::getId, AddressDto -> AddressDto)));
        return all;

    }



}
