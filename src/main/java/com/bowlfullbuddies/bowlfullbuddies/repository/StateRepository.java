package com.bowlfullbuddies.bowlfullbuddies.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bowlfullbuddies.bowlfullbuddies.entity.State;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Long> {
    List<State> findByCountryId(Long countryId);
}
