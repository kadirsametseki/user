package com.example.userproject.repository;

import com.example.userproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameAndSurname(String name, String surname);
}
