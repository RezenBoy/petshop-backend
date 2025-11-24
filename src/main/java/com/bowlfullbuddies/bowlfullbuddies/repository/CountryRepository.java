package com.bowlfullbuddies.bowlfullbuddies.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bowlfullbuddies.bowlfullbuddies.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {}
