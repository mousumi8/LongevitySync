package com.example.longevitysync.controller;

import com.example.longevitysync.model.LSUser;
import com.example.longevitysync.repository.LSUserRepository;
import com.example.longevitysync.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class LSUserController {

    @Autowired
    private LSUserRepository userRepository;

    // Get all users
    @GetMapping
    public ResponseEntity<List<LSUser>> getAllUsers() {
        List<LSUser> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Get a specific user by ID
    @GetMapping("/{id}")
    public ResponseEntity<LSUser> getUserById(@PathVariable("id") Long id) {
        Optional<LSUser> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok)
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create a new user
    @PostMapping
    public ResponseEntity<LSUser> createUser(@RequestBody LSUser user) {
        try {
            LSUser savedUser = userRepository.save(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an existing user
    @PutMapping("/{id}")
    public ResponseEntity<LSUser> updateUser(@PathVariable("id") Long id, @RequestBody LSUser updatedUser) {
        Optional<LSUser> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            LSUser user = existingUser.get();
            user.setEmail(updatedUser.getEmail());
            String hashedPassword = PasswordUtils.hashPassword(user.getPassword());
            user.setPassword(hashedPassword);

            // Set the created date to the current date and time
            user.setCreatedDate(LocalDateTime.now());
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setRole(updatedUser.getRole());
            LSUser savedUser = userRepository.save(user);
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        try {
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
