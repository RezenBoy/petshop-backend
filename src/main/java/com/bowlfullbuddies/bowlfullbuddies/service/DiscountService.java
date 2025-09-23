package com.bowlfullbuddies.bowlfullbuddies.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.bowlfullbuddies.bowlfullbuddies.entity.admin.Discount;
import com.bowlfullbuddies.bowlfullbuddies.entity.admin.ProductSubCategory;
import com.bowlfullbuddies.bowlfullbuddies.repository.DiscountRepository;
import com.bowlfullbuddies.bowlfullbuddies.repository.ProductSubCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiscountService {

    private final DiscountRepository discountRepository;
    private final ProductSubCategoryRepository subCategoryRepository;

    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    public List<Discount> getDiscountsBySubCategory(Long subCategoryId) {
        return discountRepository.findByProductSubCategoryId(subCategoryId);
    }

    public Discount saveDiscount(Long subCategoryId, Discount discount) {
        ProductSubCategory subCategory = subCategoryRepository.findById(subCategoryId)
                .orElseThrow(() -> new RuntimeException("Subcategory not found"));

        discount.setProductSubCategory(subCategory);
        return discountRepository.save(discount);
    }

    public Discount updateDiscount(Long id, Discount updatedDiscount) {
        Discount existing = discountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discount not found"));

        existing.setDiscountName(updatedDiscount.getDiscountName());
        existing.setDiscountPercent(updatedDiscount.getDiscountPercent());
        return discountRepository.save(existing);
    }

    public void deleteDiscount(Long id) {
        discountRepository.deleteById(id);
    }
}
