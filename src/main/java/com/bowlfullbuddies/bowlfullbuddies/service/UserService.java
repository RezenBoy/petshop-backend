package com.bowlfullbuddies.bowlfullbuddies.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bowlfullbuddies.bowlfullbuddies.dto.AddressDto;
import com.bowlfullbuddies.bowlfullbuddies.dto.UpdateUserRequest;
import com.bowlfullbuddies.bowlfullbuddies.dto.UserDto;
import com.bowlfullbuddies.bowlfullbuddies.entity.AddressEmbeddable;
import com.bowlfullbuddies.bowlfullbuddies.entity.District;
import com.bowlfullbuddies.bowlfullbuddies.entity.customer.Users;
import com.bowlfullbuddies.bowlfullbuddies.repository.DistrictRepository;
import com.bowlfullbuddies.bowlfullbuddies.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users authenticate(String email, String rawPassword) {
        Users existing = userRepository.findByAddressEmbeddableEmail(email);
        if (existing == null) {
            throw new RuntimeException("Invalid email or password");
        }
        if (!passwordEncoder.matches(rawPassword, existing.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
        return existing;
    }

    // Register user
    public Users registerUser(Users user) {
        if (user.getAddressEmbeddable() == null || user.getAddressEmbeddable().getEmail() == null) {
            throw new IllegalArgumentException("Email is required for registration.");
        }

        // 1️⃣ Check if email exists
        Users existingUser = userRepository.findByAddressEmbeddableEmail(user.getAddressEmbeddable().getEmail());
        if (existingUser != null) {
            throw new RuntimeException("Account with this email already exists. Please login.");
        }

        // 2️⃣ Encode password before saving
        String plainPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(plainPassword));

        // 3️⃣ Save user
        return userRepository.save(user);
    }

    // Login user
    public Users loginUser(Users user) {
        if (user.getAddressEmbeddable() == null || user.getAddressEmbeddable().getEmail() == null) {
            throw new IllegalArgumentException("Email is required for login.");
        }
        String email = user.getAddressEmbeddable().getEmail();
        return authenticate(email, user.getPassword());
    }

    public Optional<Users> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<Users> findByEmail(String email) {
        Users u = userRepository.findByAddressEmbeddableEmail(email);
        return Optional.ofNullable(u);
    }

    /**
     * Update user fullName and/or address embeddable.
     * Returns updated Users entity.
     */
    public Users updateUser(Long id, UpdateUserRequest request) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + id));

        if (request.getFullName() != null) {
            user.setFullName(request.getFullName());
        }

        if (request.getAddress() != null) {
            AddressDto a = request.getAddress();
            AddressEmbeddable emb = user.getAddressEmbeddable() == null ? new AddressEmbeddable()
                    : user.getAddressEmbeddable();

            emb.setEmail(a.getEmail());
            emb.setMobileNo(a.getMobileNo());
            emb.setCity(a.getCity());
            emb.setLandMark(a.getLandMark());
            emb.setPincode(a.getPincode());

            // optional: resolve district by id if provided
            if (a.getDistrictId() != null) {
                District d = districtRepository.findById(a.getDistrictId())
                        .orElseThrow(() -> new IllegalArgumentException("District not found: " + a.getDistrictId()));
                emb.setDistrict(d);
            }
            user.setAddressEmbeddable(emb);
        }

        return userRepository.save(user);
    }

    /**
     * Save uploaded file bytes into AddressEmbeddable.image
     */
    public Users saveAddressImage(Long id, MultipartFile file) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + id));

        try {
            if (user.getAddressEmbeddable() == null) {
                user.setAddressEmbeddable(new AddressEmbeddable());
            }
            user.getAddressEmbeddable().setImage(file.getBytes());
            return userRepository.save(user);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    public Users findUserByEmail(String email) {
        return userRepository.findByAddressEmbeddableEmail(email);
    }

    // ---------------- optional DTO mapping helpers ----------------
    // If you prefer returning DTOs to the controller (safer), use this:
    public UserDto toDto(Users u) {
        if (u == null) {
            return null;
        }
        AddressDto ad = null;
        if (u.getAddressEmbeddable() != null) {
            ad = new AddressDto();
            ad.setEmail(u.getAddressEmbeddable().getEmail());
            ad.setMobileNo(u.getAddressEmbeddable().getMobileNo());
            ad.setCity(u.getAddressEmbeddable().getCity());
            ad.setLandMark(u.getAddressEmbeddable().getLandMark());
            ad.setPincode(u.getAddressEmbeddable().getPincode());
            ad.setDistrictId(
                    u.getAddressEmbeddable().getDistrict() != null ? u.getAddressEmbeddable().getDistrict().getId()
                            : null);
            ad.setDistrictName(u.getAddressEmbeddable().getDistrict() != null
                    ? u.getAddressEmbeddable().getDistrict().getDistrictName()
                    : null);
        }
        UserDto dto = new UserDto();
        dto.setId(u.getId());
        dto.setFullName(u.getFullName());
        dto.setUsersCategory(u.getUsersCategory());
        dto.setAddress(ad);
        return dto;
    }
}
