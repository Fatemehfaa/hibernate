package com.example.hibernate.address;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {
    AddressService addressService;

    @PostMapping("/saveAddress")
     public ResponseEntity<AddressDto> saveAddress(@RequestBody AddressDto addressDto) {
         return ResponseEntity.ok(addressService.saveAddress(addressDto));
     }

     @GetMapping("/findAddressById/{id}")
     public ResponseEntity<AddressDto> findAddressById(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.findById(id));
     }

     @PutMapping("/update")
     public ResponseEntity<AddressDto> update(@RequestBody AddressDto addressDto) {
        return ResponseEntity.ok(addressService.updateAddress(addressDto));
     }

     @DeleteMapping("/deleteById/{id}")
     public void deleteById(@PathVariable Long id) {
        addressService.deleteById(id);
     }

     @GetMapping("/getAllAddress/")
     public ResponseEntity<List<AddressDto>> getAllAddresses() {
        return ResponseEntity.ok(addressService.findAllAddress());
     }




}
