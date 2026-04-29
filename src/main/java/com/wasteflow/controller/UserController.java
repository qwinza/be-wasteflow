package com.wasteflow.controller;

import com.wasteflow.entity.User;
import com.wasteflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userRepository.save(user));
    }
}
