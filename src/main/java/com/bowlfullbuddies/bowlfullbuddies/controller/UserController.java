package com.bowlfullbuddies.bowlfullbuddies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bowlfullbuddies.bowlfullbuddies.config.JwtUtil;
import com.bowlfullbuddies.bowlfullbuddies.dto.LoginRequest;
import com.bowlfullbuddies.bowlfullbuddies.dto.LoginResponse;
import com.bowlfullbuddies.bowlfullbuddies.entity.customer.Users;
import com.bowlfullbuddies.bowlfullbuddies.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    // Inject JwtUtil properly
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Users user) {
        try {
            return ResponseEntity.ok(userService.registerUser(user));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        try {
            Users u = userService.authenticate(req.getEmail(), req.getPassword());
            String token = jwtUtil.generateToken(u.getAddressEmbeddable().getEmail(), u.getId());
            return ResponseEntity.ok(new LoginResponse(token, u.getId(), u.getFullName()));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(401).body(ex.getMessage());
        }
    }

    // other endpoints (if any) ...
}
