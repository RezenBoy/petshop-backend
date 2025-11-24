package com.bowlfullbuddies.bowlfullbuddies.controller.user;

import com.bowlfullbuddies.bowlfullbuddies.dto.AddressDto;
import com.bowlfullbuddies.bowlfullbuddies.dto.UpdateUserRequest;
import com.bowlfullbuddies.bowlfullbuddies.dto.UserDto;
import com.bowlfullbuddies.bowlfullbuddies.entity.AddressEmbeddable;
import com.bowlfullbuddies.bowlfullbuddies.entity.customer.Users;
import com.bowlfullbuddies.bowlfullbuddies.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * User profile controller.
 * - Returns DTOs (UserDto) to frontend (avoid exposing JPA internals).
 * - Demo auth via X-USER-ID header (replace with real security later).
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String email = authentication.getName(); // we set subject as email
        Users u = userService.findUserByEmail(email);
        if (u == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(u); // or map to DTO
    }

    // GET /api/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        Optional<Users> uOpt = userService.findById(id);
        return uOpt.map(u -> ResponseEntity.ok(toDto(u))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // PUT /api/users/{id} - update profile (name + address)
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest request) {
        try {
            // userService.updateUser currently returns Users (entity) in your service;
            // adapt if it returns UserDto instead.
            Users updated = userService.updateUser(id, request);
            return ResponseEntity.ok(toDto(updated));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(500).build();
        }
    }

    // POST /api/users/{id}/address/image - upload address image
    @PostMapping(value = "/{id}/address/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserDto> uploadAddressImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            Users updated = userService.saveAddressImage(id, file);
            return ResponseEntity.ok(toDto(updated));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(500).build();
        }
    }

    // === Simple mapper Users -> UserDto (keeps controller independent of mapper impl) ===
    private UserDto toDto(Users u) {
        if (u == null) return null;
        UserDto dto = new UserDto();
        dto.setId(u.getId());
        dto.setFullName(u.getFullName());
        dto.setUsersCategory(u.getUsersCategory());

        AddressEmbeddable a = u.getAddressEmbeddable();
        if (a != null) {
            AddressDto ad = new AddressDto();
            ad.setEmail(a.getEmail());
            ad.setMobileNo(a.getMobileNo());
            ad.setCity(a.getCity());
            ad.setLandMark(a.getLandMark());
            ad.setPincode(a.getPincode());
            if (a.getDistrict() != null) {
                ad.setDistrictId(a.getDistrict().getId());
                ad.setDistrictName(a.getDistrict().getDistrictName());
            }
            dto.setAddress(ad);
        }
        return dto;
    }
}
