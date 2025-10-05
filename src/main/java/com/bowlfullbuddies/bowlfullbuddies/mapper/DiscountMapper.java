package com.bowlfullbuddies.bowlfullbuddies.mapper;

import com.bowlfullbuddies.bowlfullbuddies.dto.DiscountDTO;
import com.bowlfullbuddies.bowlfullbuddies.dto.CreateDiscountDTO;
import com.bowlfullbuddies.bowlfullbuddies.entity.admin.Discount;
import com.bowlfullbuddies.bowlfullbuddies.entity.admin.ProductSubCategory;
import org.springframework.stereotype.Component;

@Component
public class DiscountMapper {

    public DiscountDTO toDTO(Discount discount) {
        if (discount == null)
            return null;

        String categoryName = null;
        String subCategoryName = null;
        Long subCategoryId = null;

        if (discount.getProductSubCategory() != null) {
            subCategoryId = discount.getProductSubCategory().getId();
            subCategoryName = discount.getProductSubCategory().getSubCategoryName();

            if (discount.getProductSubCategory().getProductCategory() != null) {
                categoryName = discount.getProductSubCategory().getProductCategory().getCategoryName();
            }
        }
        return new DiscountDTO(
                discount.getId(),
                discount.getDiscountName(),
                discount.getDiscountPercent(),
                discount.getProductSubCategory() != null ? discount.getProductSubCategory().getId() : null,
                discount.getProductSubCategory() != null ? discount.getProductSubCategory().getSubCategoryName() : null,
                discount.getProductSubCategory() != null && discount.getProductSubCategory().getProductCategory() != null ? discount.getProductSubCategory().getProductCategory().getCategoryName() : null);

    }

    public Discount toEntity(CreateDiscountDTO dto, ProductSubCategory subCategory) {
        Discount d = new Discount();
        d.setDiscountName(dto.getDiscountName());
        d.setDiscountPercent(dto.getDiscountPercent());
        d.setProductSubCategory(subCategory);
        return d;
    }

    public void updateEntity(Discount discount, CreateDiscountDTO dto, ProductSubCategory subCategory) {
        discount.setDiscountName(dto.getDiscountName());
        discount.setDiscountPercent(dto.getDiscountPercent());
        discount.setProductSubCategory(subCategory);
    }
}
