package com.bowlfullbuddies.bowlfullbuddies.controller.user;

import com.bowlfullbuddies.bowlfullbuddies.dto.PetDto;
import com.bowlfullbuddies.bowlfullbuddies.entity.customer.Pet;
import com.bowlfullbuddies.bowlfullbuddies.entity.customer.Users;
import com.bowlfullbuddies.bowlfullbuddies.repository.PetRepository;
import com.bowlfullbuddies.bowlfullbuddies.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pets")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class PetController {

    private final PetRepository petRepository;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<PetDto>> getMyPets(Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) return ResponseEntity.status(401).build();
        Users u = userService.findUserByEmail(auth.getName());
        if(u == null) return ResponseEntity.notFound().build();
        
        List<PetDto> pets = petRepository.findByOwnerId(u.getId())
            .stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(pets);
    }

    @PostMapping
    public ResponseEntity<PetDto> addPet(@RequestBody PetDto dto, Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) return ResponseEntity.status(401).build();
        Users u = userService.findUserByEmail(auth.getName());
        if(u == null) return ResponseEntity.notFound().build();

        Pet p = new Pet();
        p.setName(dto.getName());
        p.setType(dto.getType());
        p.setBreed(dto.getBreed());
        p.setAge(dto.getAge());
        
        // Ensure default UI gradient colors are assigned automatically if missing
        if (dto.getColor() == null || dto.getColor().isEmpty()) {
            p.setColor("from-pink-400 to-rose-500");
        } else {
            p.setColor(dto.getColor());
        }

        p.setOwner(u);

        Pet saved = petRepository.save(p);
        return ResponseEntity.ok(toDto(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id, Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) return ResponseEntity.status(401).build();
        Users u = userService.findUserByEmail(auth.getName());
        if(u == null) return ResponseEntity.notFound().build();

        Pet p = petRepository.findById(id).orElse(null);
        if (p == null || !p.getOwner().getId().equals(u.getId())) {
            return ResponseEntity.notFound().build();
        }

        petRepository.delete(p);
        return ResponseEntity.ok().build();
    }

    private PetDto toDto(Pet p) {
        PetDto dto = new PetDto();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setType(p.getType());
        dto.setBreed(p.getBreed());
        dto.setAge(p.getAge());
        dto.setColor(p.getColor());
        return dto;
    }
}
