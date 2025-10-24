package com.bowlfullbuddies.bowlfullbuddies.controller.user;

import com.bowlfullbuddies.bowlfullbuddies.dto.ProductReviewDTO;
import com.bowlfullbuddies.bowlfullbuddies.entity.customer.ProductReview;
import com.bowlfullbuddies.bowlfullbuddies.service.ProductReviewService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/{productId}/reviews")
public class ProductReviewController {

    private final ProductReviewService reviewService;

    public ProductReviewController(ProductReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<ProductReviewDTO> getReviews(@PathVariable Long productId) {
        return reviewService.getReviewsByProductId(productId);
    }

    @PostMapping
    public ResponseEntity<String> addReview(
            @PathVariable Long productId,
            @RequestBody ProductReview review) {

        reviewService.addReview(productId, review);
        return ResponseEntity.ok("Review added successfully!");
    }

    @PostMapping("/{reviewId}/helpful")
    public ResponseEntity<String> markHelpful(@PathVariable Long reviewId) {
        reviewService.incrementHelpful(reviewId);
        return ResponseEntity.ok("Marked helpful!");
    }
}
