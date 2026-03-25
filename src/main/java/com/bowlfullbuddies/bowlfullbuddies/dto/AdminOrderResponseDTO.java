package com.bowlfullbuddies.bowlfullbuddies.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminOrderResponseDTO {
    private Long id;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private LocalDateTime orderDateTime;
    private String orderStatus;
    private Double totalPrice;
    private String paymentMode;
    private String shippingAddress;
    private List<OrderItemResponseDTO> items;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemResponseDTO {
        private String productName;
        private Integer quantity;
        private Double price;
        private Long productId;
    }
}
