// DiscountDTO.java (Response DTO)
package com.bowlfullbuddies.bowlfullbuddies.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountDTO {
    private Long id;
    private String discountName;
    private Double discountPercent;
    private Long subCategoryId;
    private String subCategoryName;
    private String categoryName;
}
