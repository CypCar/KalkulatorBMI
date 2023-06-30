package com.BMI.repository;

import java.util.List;
import java.util.Optional;

import com.BMI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);
    Optional<User> findByUserId(Long userId);
}


