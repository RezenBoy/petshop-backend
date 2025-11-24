package com.bowlfullbuddies.bowlfullbuddies.mapper;

import com.bowlfullbuddies.bowlfullbuddies.dto.AddressDto;
import com.bowlfullbuddies.bowlfullbuddies.dto.UserDto;
import com.bowlfullbuddies.bowlfullbuddies.entity.customer.Users;
import com.bowlfullbuddies.bowlfullbuddies.entity.AddressEmbeddable;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static UserDto toDto(Users u) {
        if (u == null) return null;
        UserDto dto = new UserDto();
        dto.setId(u.getId());
        dto.setFullName(u.getFullName());
        dto.setUsersCategory(u.getUsersCategory());
        if (u.getAddressEmbeddable() != null) {
            AddressEmbeddable ae = u.getAddressEmbeddable();
            AddressDto ad = new AddressDto();
            ad.setEmail(ae.getEmail());
            ad.setMobileNo(ae.getMobileNo());
            ad.setCity(ae.getCity());
            ad.setLandMark(ae.getLandMark());
            ad.setPincode(ae.getPincode());
            ad.setDistrictId(ae.getDistrict() != null ? ae.getDistrict().getId() : null);
            ad.setDistrictName(ae.getDistrict() != null ? ae.getDistrict().getDistrictName() : null);
            dto.setAddress(ad);
        }
        return dto;
    }
}
