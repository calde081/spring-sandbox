package com.example.springmongodemo.repository;
import com.example.springmongodemo.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);

}