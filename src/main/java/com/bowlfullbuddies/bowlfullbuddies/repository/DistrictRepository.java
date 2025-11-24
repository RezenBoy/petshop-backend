package com.bowlfullbuddies.bowlfullbuddies.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bowlfullbuddies.bowlfullbuddies.entity.District;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Long> {
    List<District> findByStateId(Long stateId);
}
