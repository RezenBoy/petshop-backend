package com.bowlfullbuddies.bowlfullbuddies.dto;

import java.util.List;
import lombok.Data;

@Data
public class OrderRequestDTO {
    private AddressDto shippingAddress;
    private String paymentMode; // e.g., "CASH" or "ONLINE"
    private List<CartItemDTO> cartItems;
}
