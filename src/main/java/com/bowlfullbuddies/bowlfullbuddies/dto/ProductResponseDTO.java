package com.bowlfullbuddies.bowlfullbuddies.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {
    private Long id;
    private String productName;
    private String description;
    private Double mrp;
    private Double price;
    private Integer quantity;
    private String brand;
    private String category;
    private String subCategory;
    private List<String> sizes;
    private List<String> colors;
    private List<String> imageUrls;
}
