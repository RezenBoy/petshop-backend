package com.bowlfullbuddies.bowlfullbuddies.service;

import com.bowlfullbuddies.bowlfullbuddies.dto.ProductReviewDTO;
import com.bowlfullbuddies.bowlfullbuddies.entity.admin.Product;
import com.bowlfullbuddies.bowlfullbuddies.entity.customer.ProductReview;
import com.bowlfullbuddies.bowlfullbuddies.repository.ProductRepository;
import com.bowlfullbuddies.bowlfullbuddies.repository.ProductReviewRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductReviewService {

    private final ProductReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    public ProductReviewService(ProductReviewRepository reviewRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
    }

    public List<ProductReviewDTO> getReviewsByProductId(Long productId) {
        List<ProductReview> reviews = reviewRepository.findByProductId(productId);

        return reviews.stream()
                .map(review -> new ProductReviewDTO(
                        review.getReviewerName(),
                        review.getComment(),
                        review.getRating(),
                        review.getHelpfulCount()
                ))  
                .collect(Collectors.toList());
    }

    public ProductReview addReview(Long productId, ProductReview review) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        review.setProduct(product);
        return reviewRepository.save(review);
    }

    public void incrementHelpful(Long reviewId) {
        ProductReview review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        review.setHelpfulCount(review.getHelpfulCount() + 1);
        reviewRepository.save(review);
    }
}
