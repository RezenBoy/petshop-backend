package com.bowlfullbuddies.bowlfullbuddies.controller.user;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bowlfullbuddies.bowlfullbuddies.entity.admin.ProductCategory;
import com.bowlfullbuddies.bowlfullbuddies.repository.ProductCategoryRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class CategoryController {

    private final ProductCategoryRepository categoryRepository;

    @GetMapping
    public List<ProductCategory> getAllCategories() {
        return categoryRepository.findAll();
    }
}
