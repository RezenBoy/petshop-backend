package com.bowlfullbuddies.bowlfullbuddies.dto;

import com.bowlfullbuddies.bowlfullbuddies.model.ProductColor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductRequestDTO {
    private String productname;
    private String hsnCode;
    private String barCode;
    private String modelNo;
    private String serialNo;
    private String description;
    private Integer quantity;
    private Integer minimumStock;
    private Double costPrice;
    private Double mrp;
    private Long brandId;
    private Long taxId;
    private Long categoryId;
    private Long productSubCategoryId;
    private List<String> sizes;
    private List<ProductColor> colors;
    private String measurementUnit;
}
