package com.bowlfullbuddies.bowlfullbuddies.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bowlfullbuddies.bowlfullbuddies.entity.admin.ProductCategory;
import com.bowlfullbuddies.bowlfullbuddies.entity.admin.ProductSubCategory;
import com.bowlfullbuddies.bowlfullbuddies.repository.ProductCategoryRepository;
import com.bowlfullbuddies.bowlfullbuddies.repository.ProductSubCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductAndSubProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductSubCategoryRepository productSubCategoryRepository;

    // ------------------ CATEGORY ------------------

    public List<ProductCategory> findAllProductCatList() {
        return productCategoryRepository.findAll();
    }

    public ProductCategory saveProductCat(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    public ProductCategory findByProductCatId(Long id) {
        return productCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public void deleteProductCatById(Long id) {
        productCategoryRepository.deleteById(id);
    }

    // ------------------ SUB-CATEGORY ------------------

    public List<ProductSubCategory> findAllProductSubCatList() {
        return productSubCategoryRepository.findAll();
    }

    public List<ProductSubCategory> findAllProductSubCatListByCategoryId(Long categoryId) {
        return productSubCategoryRepository.findByProductCategoryId(categoryId);
    }

    // ✅ Correct: Save subcategory and bind with category
    public ProductSubCategory saveProductSubCategory(Long categoryId, ProductSubCategory subCategory) {
        ProductCategory category = productCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        subCategory.setProductCategory(category);
        return productSubCategoryRepository.save(subCategory);
    }

    // ✅ Update subcategory
    public ProductSubCategory updateProductSubCategory(Long id, ProductSubCategory subCategory) {
        ProductSubCategory existing = productSubCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SubCategory not found"));

        existing.setSubCategoryName(subCategory.getSubCategoryName());
        // keep same category
        return productSubCategoryRepository.save(existing);
    }

    public void deleteProductSubCatById(Long id) {
        productSubCategoryRepository.deleteById(id);
    }

    public List<ProductSubCategory> searchSubCategoriesByName(String keyword) {
        return productSubCategoryRepository.searchBySubCategoryName(keyword);
    }
}
