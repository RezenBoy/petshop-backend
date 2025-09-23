package com.bowlfullbuddies.bowlfullbuddies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bowlfullbuddies.bowlfullbuddies.entity.admin.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    // JpaRepository already provides:
    // save(), findById(), findAll(), deleteById()
}
