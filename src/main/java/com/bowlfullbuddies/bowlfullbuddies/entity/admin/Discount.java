package com.bowlfullbuddies.bowlfullbuddies.entity.admin;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.bowlfullbuddies.bowlfullbuddies.enums.DiscountStatus;
import com.bowlfullbuddies.bowlfullbuddies.enums.DiscountType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String discountName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDateTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDateTime;
    @Enumerated(EnumType.STRING)
    private DiscountType discountType;
    private Double discountValue;
    
    @Enumerated(EnumType.STRING)
    private DiscountStatus discountStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id")
    private ProductSubCategory productSubCategory;

}
