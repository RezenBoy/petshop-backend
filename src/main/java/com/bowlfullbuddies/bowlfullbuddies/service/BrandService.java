package com.bowlfullbuddies.bowlfullbuddies.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bowlfullbuddies.bowlfullbuddies.entity.admin.Brand;
import com.bowlfullbuddies.bowlfullbuddies.repository.BrandRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    // Get all brands
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    // Get single brand
    public Brand findById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));
    }

    // Save new brand
    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    // Update brand
    public Brand update(Long id, Brand brandDetails) {
        Brand brand = findById(id);
        brand.setBrandName(brandDetails.getBrandName());
        brand.setDescription(brandDetails.getDescription());
        brand.setActive(brandDetails.isActive());
        return brandRepository.save(brand);
    }

    // Delete brand
    public void delete(Long id) {
        brandRepository.deleteById(id);
    }
}
