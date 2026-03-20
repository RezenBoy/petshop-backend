package com.bowlfullbuddies.bowlfullbuddies.repository;

import com.bowlfullbuddies.bowlfullbuddies.entity.customer.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByOwnerId(Long ownerId);
}
