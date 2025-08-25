package com.bowlfullbuddies.bowlfullbuddies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bowlfullbuddies.bowlfullbuddies.entity.customer.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByAddressEmbeddableEmail(String email);
}
