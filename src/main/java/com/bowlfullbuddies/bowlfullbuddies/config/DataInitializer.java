package com.bowlfullbuddies.bowlfullbuddies.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.context.event.ApplicationReadyEvent;

@Component
public class DataInitializer {

    @PersistenceContext
    private EntityManager entityManager;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void insertInitialData() {
        Long count = ((Number) entityManager.createNativeQuery(
                "SELECT COUNT(*) FROM product_category"
        ).getSingleResult()).longValue();

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
    }
}
