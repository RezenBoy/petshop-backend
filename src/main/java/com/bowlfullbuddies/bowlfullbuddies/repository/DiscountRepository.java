package com.bowlfullbuddies.bowlfullbuddies.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bowlfullbuddies.bowlfullbuddies.entity.admin.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
    List<Discount> findByProductSubCategoryId(Long subCategoryId);
}
