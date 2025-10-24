package com.bowlfullbuddies.bowlfullbuddies.config;

import com.bowlfullbuddies.bowlfullbuddies.entity.customer.ProductReview;
import com.bowlfullbuddies.bowlfullbuddies.entity.admin.Product;
import com.bowlfullbuddies.bowlfullbuddies.repository.ProductReviewRepository;
import com.bowlfullbuddies.bowlfullbuddies.repository.ProductRepository;
import com.bowlfullbuddies.bowlfullbuddies.repository.ProductCategoryRepository;
import com.bowlfullbuddies.bowlfullbuddies.repository.ProductSubCategoryRepository;
import com.bowlfullbuddies.bowlfullbuddies.repository.BrandRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.event.ApplicationReadyEvent;

import java.util.List;

@Component
public class DataInitializer {

    @PersistenceContext
    private EntityManager entityManager;

    private final ProductReviewRepository reviewRepo;
    private final ProductRepository productRepo;

    public DataInitializer(
            ProductReviewRepository reviewRepo,
            ProductRepository productRepo,
            BrandRepository brandRepo,
            ProductCategoryRepository categoryRepo,
            ProductSubCategoryRepository subCategoryRepo
    ) {
        this.reviewRepo = reviewRepo;
        this.productRepo = productRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void insertInitialData() {

        // ✅ Insert initial categories/subcategories (your existing code)
        Long count = ((Number) entityManager.createNativeQuery(
                "SELECT COUNT(*) FROM product_category").getSingleResult()).longValue();

        if (count == 0) {
            entityManager.createNativeQuery("""
                        INSERT INTO product_category (category_name, description) VALUES
                        ('Dog', 'Dog products'),
                        ('Cat', 'Cat products'),
                        ('General', 'General products');
                    """).executeUpdate();

            entityManager.createNativeQuery("""
                        INSERT INTO product_sub_category (sub_category_name, product_category_id) VALUES
                        ('Food', 1), ('Cages', 1), ('Bowls', 1), ('Grooming', 1),
                        ('Walk Essentials', 1), ('Toys', 1), ('Accessories', 1),
                        ('Food', 2), ('Litter', 2), ('Toys', 2), ('Accessories', 2),
                        ('Grooming', 3), ('Accessories', 3), ('Toys', 3);
                    """).executeUpdate();
        }

        // ✅ Insert dummy reviews if none exist
        if (reviewRepo.count() == 0) {
            List<Product> products = productRepo.findAll();

            if (!products.isEmpty()) {
                Product product1 = products.get(0); // Just pick first product
                Product product2 = products.size() > 1 ? products.get(1) : product1;

                ProductReview r1 = new ProductReview();
                r1.setReviewerName("Simranjeet Singh");
                r1.setComment("Excellent product! My dog absolutely loves it.");
                r1.setRating(5);
                r1.setHelpfulCount(10);
                r1.setProduct(product1);

                ProductReview r2 = new ProductReview();
                r2.setReviewerName("Aarav Mehta");
                r2.setComment("Good quality, but the size was slightly smaller than expected.");
                r2.setRating(4);
                r2.setHelpfulCount(3);
                r2.setProduct(product1);

                ProductReview r3 = new ProductReview();
                r3.setReviewerName("Priya Sharma");
                r3.setComment("Perfect for my cat, packaging was neat and delivery was on time.");
                r3.setRating(5);
                r3.setHelpfulCount(8);
                r3.setProduct(product2);

                ProductReview r4 = new ProductReview();
                r4.setReviewerName("Rohit Kumar");
                r4.setComment("Not bad, but could improve durability.");
                r4.setRating(3);
                r4.setHelpfulCount(1);
                r4.setProduct(product2);

                reviewRepo.saveAll(List.of(r1, r2, r3, r4));

                System.out.println("✅ Dummy product reviews inserted successfully!");
            } else {
                System.out.println("⚠️ No products found — skipping review insertion.");
            }
        } else {
            System.out.println("ℹ️ Reviews already exist — skipping dummy review insertion.");
        }
    }
}
