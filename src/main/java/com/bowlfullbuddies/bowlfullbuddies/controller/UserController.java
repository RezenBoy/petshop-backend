package com.bowlfullbuddies.bowlfullbuddies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bowlfullbuddies.bowlfullbuddies.entity.customer.Users;
import com.bowlfullbuddies.bowlfullbuddies.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/register")
    public ResponseEntity<?> getRegistrationPage() {
        // Return the registration page
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")   
    public ResponseEntity<?> registerUser(@RequestBody Users user) {
        // Implement registration logic
        return ResponseEntity.ok(userRepository.save(user));
    }

    @GetMapping("/login")
    public ResponseEntity<?> getLoginPage() {
        // Return the login page
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Users user) {
        Users existingUser = userRepository.findByAddressEmbeddableEmail(user.getAddressEmbeddable().getEmail());

        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok(existingUser); // success
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
}
