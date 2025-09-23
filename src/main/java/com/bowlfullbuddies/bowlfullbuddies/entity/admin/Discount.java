package com.bowlfullbuddies.bowlfullbuddies.entity.admin;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String discountName;
    private Double discountPercent;

    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    // @JsonBackReference   // 👈 prevents recursion
    private ProductSubCategory productSubCategory;
}
