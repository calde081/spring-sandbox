package com.example.springmongodemo.controllers;

import com.example.springmongodemo.models.User;
import com.example.springmongodemo.payload.UserRequest;
import com.example.springmongodemo.respository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/")
public class UserController {

    @Autowired
    UserRepo userRepository;




    @PostMapping("/user")
    public ResponseEntity<?> postPost(@RequestBody UserRequest newPostUserRequest) {
        User newUser = new User(newPostUserRequest.getUserName(), newPostUserRequest.getBio());
        userRepository.save(newUser);
        return ResponseEntity.ok("Post created successfully!");
    }


    @GetMapping("/user")
    public String getUser(@RequestParam String username) {
        if(userRepository.findByUsername(username).isPresent()) {
            User user = userRepository.findByUsername(username).get();
            String userBio = user.getUsername() + " " + user.getBio();
            return userBio;
        }
        return null;
    }

    @GetMapping("/test")
    public String testApi() {
        return "working";
    }
}
