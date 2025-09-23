package com.bowlfullbuddies.bowlfullbuddies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bowlfullbuddies.bowlfullbuddies.entity.admin.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
