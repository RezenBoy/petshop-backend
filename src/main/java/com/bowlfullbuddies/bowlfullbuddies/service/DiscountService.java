package com.bowlfullbuddies.bowlfullbuddies.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bowlfullbuddies.bowlfullbuddies.dto.CreateDiscountDTO;
import com.bowlfullbuddies.bowlfullbuddies.dto.DiscountDTO;
import com.bowlfullbuddies.bowlfullbuddies.entity.admin.Discount;
import com.bowlfullbuddies.bowlfullbuddies.entity.admin.ProductSubCategory;
import com.bowlfullbuddies.bowlfullbuddies.mapper.DiscountMapper;
import com.bowlfullbuddies.bowlfullbuddies.repository.DiscountRepository;
import com.bowlfullbuddies.bowlfullbuddies.repository.ProductSubCategoryRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class DiscountService {

    private final DiscountRepository discountRepository;
    private final ProductSubCategoryRepository subCategoryRepository;
    private final DiscountMapper discountMapper;

    public List<DiscountDTO> getAllDiscounts() {
        return discountRepository.findAll()
                .stream()
                .map(discountMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DiscountDTO saveDiscount(CreateDiscountDTO dto) {
        ProductSubCategory subCategory = subCategoryRepository.findById(dto.getSubCategoryId())
                .orElseThrow(() -> new RuntimeException("Subcategory not found"));
        Discount discount = discountMapper.toEntity(dto, subCategory);
        return discountMapper.toDTO(discountRepository.save(discount));
    }

    public DiscountDTO updateDiscount(Long id, CreateDiscountDTO dto) {
        Discount discount = discountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discount not found"));
        ProductSubCategory subCategory = subCategoryRepository.findById(dto.getSubCategoryId())
                .orElseThrow(() -> new RuntimeException("Subcategory not found"));
        discountMapper.updateEntity(discount, dto, subCategory);
        return discountMapper.toDTO(discountRepository.save(discount));
    }

    public void deleteDiscount(Long id) {
        discountRepository.deleteById(id);
    }
}
