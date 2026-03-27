package com.bowlfullbuddies.bowlfullbuddies.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bowlfullbuddies.bowlfullbuddies.dto.AdminOrderResponseDTO;
import com.bowlfullbuddies.bowlfullbuddies.entity.admin.Orders;
import com.bowlfullbuddies.bowlfullbuddies.enums.OrderStatus;
import com.bowlfullbuddies.bowlfullbuddies.repository.OrdersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminOrderService {

    private final OrdersRepository ordersRepository;

    @Transactional(readOnly = true)
    public List<AdminOrderResponseDTO> getAllOrders() {
        return ordersRepository.findAllByOrderByOrderDateTimeDesc().stream()
                .map(this::mapToDTO).toList();
    }

    @Transactional
    public AdminOrderResponseDTO updateOrderStatus(Long orderId, String status) {
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));
        
        try {
            order.setOrderStatus(OrderStatus.valueOf(status.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid order status: " + status);
        }
        
        return mapToDTO(ordersRepository.save(order));
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));
        ordersRepository.delete(order);
    }

    private AdminOrderResponseDTO mapToDTO(Orders order) {
        AdminOrderResponseDTO dto = new AdminOrderResponseDTO();
        dto.setId(order.getId());
        if (order.getOrderDateTime() != null) {
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            dto.setOrderDateTime(order.getOrderDateTime().format(formatter));
        }
        dto.setOrderStatus(order.getOrderStatus() != null ? order.getOrderStatus().name() : "N/A");
        dto.setTotalPrice(order.getTotalPrice());
        dto.setPaymentMode(order.getPaymentMode() != null ? order.getPaymentMode().name() : "N/A");

        if (order.getUsers() != null) {
            dto.setCustomerName(order.getUsers().getFullName());
        } else {
            dto.setCustomerName("Guest");
        }

        if (order.getAddressEmbeddable() != null) {
            dto.setShippingAddress(order.getAddressEmbeddable().getFullAddress());
            dto.setCustomerEmail(order.getAddressEmbeddable().getEmail() != null ? order.getAddressEmbeddable().getEmail() : "");
            dto.setCustomerPhone(order.getAddressEmbeddable().getMobileNo() != null ? order.getAddressEmbeddable().getMobileNo() : "");
        } else {
            dto.setShippingAddress("N/A");
            dto.setCustomerEmail("");
            dto.setCustomerPhone("");
        }

        if (order.getOrderItemLists() != null) {
            List<AdminOrderResponseDTO.OrderItemResponseDTO> items = order.getOrderItemLists().stream().map(i -> {
                AdminOrderResponseDTO.OrderItemResponseDTO itemDto = new AdminOrderResponseDTO.OrderItemResponseDTO();
                if (i.getProduct() != null) {
                    itemDto.setProductName(i.getProduct().getProductName());
                    itemDto.setPrice(i.getProduct().getMrp() != null ? i.getProduct().getMrp() : 0.0);
                    itemDto.setProductId(i.getProduct().getId());
                } else {
                    itemDto.setProductName("Unknown");
                    itemDto.setPrice(0.0);
                }
                itemDto.setQuantity(i.getQuantity());
                return itemDto;
            }).toList();
            dto.setItems(items);
        } else {
            dto.setItems(Collections.emptyList());
        }

        return dto;
    }
}
