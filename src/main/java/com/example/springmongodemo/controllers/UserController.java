package com.example.springmongodemo.controllers;

import com.example.springmongodemo.models.User;
import com.example.springmongodemo.payload.UserRequest;
import com.example.springmongodemo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/")
public class UserController {

    @Autowired
    UserRepo userRepository;


    @PostMapping("/linkGen")
    public ResponseEntity<?> createUser(@RequestBody UserRequest newPostUserRequest) {
        User newUser = new User(newPostUserRequest.getUsername(), newPostUserRequest.getLink());
        userRepository.save(newUser);
        return ResponseEntity.ok("Post created successfully!");
    }

    @GetMapping("/user")
    public String getUser(@RequestParam String username) {
        if(userRepository.existsByUsername(username)) {
            User user = userRepository.findByUsername(username).get();
            String userBio = user.getUsername() + " " + user.getLink();
            return userBio;
        }
        return null;
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateUser(@RequestBody UserRequest newPutUserRequest)
    {
        String username = newPutUserRequest.getUsername();
        if (userRepository.existsByUsername(username))
        {
            User user = userRepository.findByUsername(username).get();
            user.setLink(newPutUserRequest.getLink());
            userRepository.save(user);
            return ResponseEntity.ok("User updated successfully!");
        }
        else
            return ResponseEntity.ok("User not found.");
    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUser(@RequestBody UserRequest newDeleteUserRequest)
    {
        String username = newDeleteUserRequest.getUsername();
        if (userRepository.existsByUsername(username))
        {
            User user = userRepository.findByUsername(username).get();
            userRepository.delete(user);
            return ResponseEntity.ok("User deleted successfully!");
        }
        else
            return ResponseEntity.ok("User not found.");
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/test")
    public String testApi() {
        return "working";
    }
}
