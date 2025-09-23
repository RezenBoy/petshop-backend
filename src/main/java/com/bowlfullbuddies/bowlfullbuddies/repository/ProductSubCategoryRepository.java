package com.bowlfullbuddies.bowlfullbuddies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bowlfullbuddies.bowlfullbuddies.entity.admin.ProductSubCategory;

public interface ProductSubCategoryRepository extends JpaRepository<ProductSubCategory, Long> {

    // Get all subcategories by categoryId
    List<ProductSubCategory> findByProductCategoryId(Long categoryId);

    // Search subcategories by name (case-insensitive)
    @Query("SELECT s FROM ProductSubCategory s WHERE LOWER(s.subCategoryName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<ProductSubCategory> searchBySubCategoryName(@Param("keyword") String keyword);
}
