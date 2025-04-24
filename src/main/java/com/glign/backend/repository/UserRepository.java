package com.glign.backend.repository;

import com.glign.backend.jpa.entity.User;
import com.glign.backend.util.Constant;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(@NotBlank(message = "email is required") @Email(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = Constant.INVALID_EMAIL) String email);
    User findByUuid(UUID uuid);
    User findByEmail(@NotBlank(message = "email is required") @Email(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = Constant.INVALID_EMAIL) String email);
}
