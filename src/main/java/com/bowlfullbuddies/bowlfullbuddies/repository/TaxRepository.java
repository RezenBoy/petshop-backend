package com.bowlfullbuddies.bowlfullbuddies.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bowlfullbuddies.bowlfullbuddies.entity.admin.Tax;

public interface TaxRepository extends JpaRepository<Tax, Long> {
}
