package com.example.restaurant.repository;

import com.example.restaurant.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}