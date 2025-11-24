package com.bowlfullbuddies.bowlfullbuddies.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private String email;
    private String mobileNo;
    private String city;
    private String landMark;
    private String pincode;
    private Long districtId;
    private String districtName;
    // image is handled separately via upload endpoint; you can add a URL if you store images externally
}
