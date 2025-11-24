package com.bowlfullbuddies.bowlfullbuddies.dto;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String fullName;
    private AddressDto address;
}
