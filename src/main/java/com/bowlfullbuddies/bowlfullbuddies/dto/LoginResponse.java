package com.bowlfullbuddies.bowlfullbuddies.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor        // generates LoginResponse(String token, Long userId, String fullName)
@NoArgsConstructor         // optional: creates empty constructor
public class LoginResponse {
    private String token;
    private Long userId;
    private String fullName;
}
