package com.example.restaurant.repository;

import com.example.restaurant.model.User;
import com.example.restaurant.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    // ...existing code...
}

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    Restaurant findByName(String name);
}
