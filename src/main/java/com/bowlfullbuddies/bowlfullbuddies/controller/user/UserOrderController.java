package com.bowlfullbuddies.bowlfullbuddies.controller.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bowlfullbuddies.bowlfullbuddies.dto.OrderRequestDTO;
import com.bowlfullbuddies.bowlfullbuddies.dto.UserOrderResponseDTO;
import com.bowlfullbuddies.bowlfullbuddies.entity.admin.Orders;
import com.bowlfullbuddies.bowlfullbuddies.entity.customer.Users;
import com.bowlfullbuddies.bowlfullbuddies.service.UserOrderService;
import com.bowlfullbuddies.bowlfullbuddies.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user/orders")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class UserOrderController {

    private final UserOrderService userOrderService;
    private final UserService userService;

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(Authentication authentication, @RequestBody OrderRequestDTO request) {
        Long userId = null;
        if (authentication != null && authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser")) {
            Users u = userService.findUserByEmail(authentication.getName());
            if (u != null) {
                userId = u.getId();
            }
        }

        try {
            Orders order = userOrderService.checkout(userId, request);
            return ResponseEntity.ok("Order placed successfully with ID: " + order.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getUserOrders(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        Users u = userService.findUserByEmail(authentication.getName());
        if (u == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        List<UserOrderResponseDTO> orders = userOrderService.getUserOrders(u.getId());
        return ResponseEntity.ok(orders);
    }
}
