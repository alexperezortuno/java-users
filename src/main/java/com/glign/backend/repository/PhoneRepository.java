package com.glign.backend.repository;

import com.glign.backend.jpa.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {
    Optional<Phone> findByUserIdAndNumber(Long userId, String number);
    void deleteById(Long id);
}
