package com.bowlfullbuddies.bowlfullbuddies.entity.admin;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id")
    // @JsonBackReference  
    private ProductSubCategory productSubCategory;
}
