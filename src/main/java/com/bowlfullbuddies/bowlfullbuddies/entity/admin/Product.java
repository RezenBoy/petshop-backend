package com.bowlfullbuddies.bowlfullbuddies.entity.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.bowlfullbuddies.bowlfullbuddies.model.ProductColor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private String hsnCode;
    private String barCode;
    private String modelNo;
    private String serialNo;

    @Lob
    private String description;

    private Integer quantity;
    private Integer minimumStock;

    private Double costPrice;
    private Double mrp;

    private String measurementUnit; // cm / inch / other

    /**
     * Sizes: stored as a simple string list
     * e.g. ["S", "M", "L", "18"]
     */
    @ElementCollection
    @CollectionTable(name = "product_sizes", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "size")
    @Fetch(FetchMode.SUBSELECT)
    private List<String> sizes = new ArrayList<>();

    /**
     * Colors: stored as a list of embeddables with name + code
     */
    @ElementCollection
    @CollectionTable(name = "product_colors", joinColumns = @JoinColumn(name = "product_id"))
    @Fetch(FetchMode.SUBSELECT)
    private List<ProductColor> colors = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Fetch(FetchMode.SUBSELECT)
    private List<ProductImages> productImages = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "subcategory-product")
    private ProductSubCategory productSubCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tax_id")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Tax tax;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Brand brand;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Product))
            return false;
        Product other = (Product) o;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
