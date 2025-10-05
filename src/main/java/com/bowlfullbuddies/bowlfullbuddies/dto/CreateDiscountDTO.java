// CreateDiscountDTO.java (Request DTO for create/update)
package com.bowlfullbuddies.bowlfullbuddies.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDiscountDTO {
    private String discountName;
    private Double discountPercent;
    private Long subCategoryId;
}
