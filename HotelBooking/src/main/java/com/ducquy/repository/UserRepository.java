package com.ducquy.repository;

import com.ducquy.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> { // Repository cho thực thể User

    Optional<User> findByEmail(String email); // Tìm người dùng theo email
}
