package com.bowlfullbuddies.bowlfullbuddies.controller.admin;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bowlfullbuddies.bowlfullbuddies.entity.admin.Brand;
import com.bowlfullbuddies.bowlfullbuddies.service.BrandService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/brands")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    // Get all brands
    @GetMapping
    public ResponseEntity<List<Brand>> getAllBrands() {
        return ResponseEntity.ok(brandService.findAll());
    }

    // Get brand by ID
    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable Long id) {
        return ResponseEntity.ok(brandService.findById(id));
    }

    // Create brand
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) {
        return ResponseEntity.ok(brandService.save(brand));
    }

    // Update brand
    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<Brand> updateBrand(
            @PathVariable Long id,
            @RequestBody Brand brand) {
        return ResponseEntity.ok(brandService.update(id, brand));
    }

    // Delete brand
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        brandService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
