package com.bowlfullbuddies.bowlfullbuddies.controller.admin;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bowlfullbuddies.bowlfullbuddies.entity.admin.ProductCategory;
import com.bowlfullbuddies.bowlfullbuddies.entity.admin.ProductSubCategory;
import com.bowlfullbuddies.bowlfullbuddies.service.ProductAndSubProductCategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/categories")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class ProductAndSubProductCategoryController {

    private final ProductAndSubProductCategoryService productCatAndSubCatService;

    // ------------------ CATEGORY APIs ------------------

    @GetMapping
    public ResponseEntity<List<ProductCategory>> getAllCategories() {
        return ResponseEntity.ok(productCatAndSubCatService.findAllProductCatList());
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<ProductCategory> createCategory(@RequestBody ProductCategory productCategory) {
        return ResponseEntity.ok(productCatAndSubCatService.saveProductCat(productCategory));
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<ProductCategory> updateCategory(
            @PathVariable Long id,
            @RequestBody ProductCategory productCategory) {
        productCategory.setId(id);
        return ResponseEntity.ok(productCatAndSubCatService.saveProductCat(productCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        productCatAndSubCatService.deleteProductCatById(id);
        return ResponseEntity.noContent().build();
    }

    // ------------------ SUB-CATEGORY APIs ------------------

    @GetMapping("/{categoryId}/subcategories")
    public ResponseEntity<List<ProductSubCategory>> getSubCategoriesByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(productCatAndSubCatService.findAllProductSubCatListByCategoryId(categoryId));
    }

    @PostMapping(value = "/{categoryId}/subcategories", consumes = "application/json")
    public ResponseEntity<ProductSubCategory> createSubCategory(
            @PathVariable Long categoryId,
            @RequestBody ProductSubCategory subCategory) {

        // Delegate to service, service will bind category
        return ResponseEntity.ok(productCatAndSubCatService.saveProductSubCategory(categoryId, subCategory));
    }

    @PutMapping(value = "/subcategories/{id}", consumes = "application/json")
    public ResponseEntity<ProductSubCategory> updateSubCategory(
            @PathVariable Long id,
            @RequestBody ProductSubCategory subCategory) {

        // Use service instead of repository
        return ResponseEntity.ok(productCatAndSubCatService.updateProductSubCategory(id, subCategory));
    }

    @DeleteMapping("/subcategories/{id}")
    public ResponseEntity<Void> deleteSubCategory(@PathVariable Long id) {
        productCatAndSubCatService.deleteProductSubCatById(id);
        return ResponseEntity.noContent().build();
    }

    // ------------------ IMAGE API ------------------

    // @GetMapping("/subcategories/{id}/image")
    // public ResponseEntity<byte[]> getSubCategoryImage(@PathVariable Long id) throws IOException {
    //     return ResponseEntity.ok(productCatAndSubCatService.getImageBySubCategoryId(id));
    // }
}
