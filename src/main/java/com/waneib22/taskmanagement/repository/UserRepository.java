package com.waneib22.taskmanagement.repository;

import com.waneib22.taskmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
