package com.example.hibernate.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
    Optional<AddressEntity> findById(Long id);
    void deleteById(Long id);
}
