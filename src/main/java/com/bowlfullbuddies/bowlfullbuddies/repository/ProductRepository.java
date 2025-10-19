package com.bowlfullbuddies.bowlfullbuddies.repository;

import com.bowlfullbuddies.bowlfullbuddies.entity.admin.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
