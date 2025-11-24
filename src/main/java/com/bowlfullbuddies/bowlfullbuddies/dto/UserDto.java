package com.bowlfullbuddies.bowlfullbuddies.dto;

import com.bowlfullbuddies.bowlfullbuddies.enums.UsersCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String fullName;
    private UsersCategory usersCategory;
    private AddressDto address;
}
