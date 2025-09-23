package com.bowlfullbuddies.bowlfullbuddies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bowlfullbuddies.bowlfullbuddies.entity.customer.Users;
import com.bowlfullbuddies.bowlfullbuddies.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register user
    public Users registerUser(Users user) {
        Users existingUser = userRepository.findByAddressEmbeddableEmail(user.getAddressEmbeddable().getEmail());
        if (existingUser != null) {
            throw new RuntimeException("Account with this email already exists. Please login.");
        }
        return userRepository.save(user);
    }

    // Login user
    public Users loginUser(Users user) {
        Users existingUser = userRepository.findByAddressEmbeddableEmail(user.getAddressEmbeddable().getEmail());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return existingUser;
        }
        throw new RuntimeException("Invalid email or password");
    }
}
