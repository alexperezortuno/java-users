package com.glign.backend.repository;

import com.glign.backend.jpa.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(@NotBlank(message = "email is required") @Email(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "El formato del correo no es válido") String email);
}
