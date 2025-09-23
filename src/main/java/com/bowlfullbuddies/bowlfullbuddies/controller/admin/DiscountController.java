package com.bowlfullbuddies.bowlfullbuddies.controller.admin;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bowlfullbuddies.bowlfullbuddies.entity.admin.Discount;
import com.bowlfullbuddies.bowlfullbuddies.service.DiscountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/discounts")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountService discountService;

    // Get all discounts
    @GetMapping
    public ResponseEntity<List<Discount>> getAllDiscounts() {
            List<Discount> dis = discountService.getAllDiscounts();
        return ResponseEntity.ok(discountService.getAllDiscounts());
    }

    // Get discounts by subcategory
    @GetMapping("/subcategory/{subCategoryId}")
    public ResponseEntity<List<Discount>> getDiscountsBySubCategory(@PathVariable Long subCategoryId) {
        return ResponseEntity.ok(discountService.getDiscountsBySubCategory(subCategoryId));
    }

    // Create discount for a subcategory
    @PostMapping("/subcategory/{subCategoryId}")
    public ResponseEntity<Discount> createDiscount(
            @PathVariable Long subCategoryId,
            @RequestBody Discount discount) {
        return ResponseEntity.ok(discountService.saveDiscount(subCategoryId, discount));
    }

    // Update discount
    @PutMapping("/{id}")
    public ResponseEntity<Discount> updateDiscount(@PathVariable Long id, @RequestBody Discount discount) {
        return ResponseEntity.ok(discountService.updateDiscount(id, discount));
    }

    // Delete discount
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscount(@PathVariable Long id) {
        discountService.deleteDiscount(id);
        return ResponseEntity.noContent().build();
    }
}
