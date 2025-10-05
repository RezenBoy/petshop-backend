package com.bowlfullbuddies.bowlfullbuddies.controller.admin;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bowlfullbuddies.bowlfullbuddies.dto.CreateDiscountDTO;
import com.bowlfullbuddies.bowlfullbuddies.dto.DiscountDTO;
import com.bowlfullbuddies.bowlfullbuddies.service.DiscountService;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/admin/discounts")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountService discountService;

    @GetMapping
    public ResponseEntity<List<DiscountDTO>> getAllDiscounts() {
        List<DiscountDTO> discounts = discountService.getAllDiscounts();
        return ResponseEntity.ok(discountService.getAllDiscounts());
    }

    @PostMapping
    public ResponseEntity<DiscountDTO> createDiscount(@RequestBody CreateDiscountDTO dto) {
        return ResponseEntity.ok(discountService.saveDiscount(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiscountDTO> updateDiscount(
            @PathVariable Long id,
            @RequestBody CreateDiscountDTO dto) {
        return ResponseEntity.ok(discountService.updateDiscount(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscount(@PathVariable Long id) {
        discountService.deleteDiscount(id);
        return ResponseEntity.noContent().build();
    }
}
