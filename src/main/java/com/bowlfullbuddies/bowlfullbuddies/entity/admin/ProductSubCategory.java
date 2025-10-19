package com.bowlfullbuddies.bowlfullbuddies.entity.admin;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "product_sub_category")
public class ProductSubCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String subCategoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category_id")
    @JsonBackReference(value = "category-subcategory")
	private ProductCategory productCategory;

	// @Lob
	// @Column(columnDefinition = "LONGBLOB")
	// private byte[] image;

	// @Transient
	// private MultipartFile file;

	// @OneToMany(mappedBy = "productSubCategory", cascade = CascadeType.ALL, fetch
	// = FetchType.LAZY)
	// @JsonManagedReference
	// private List<Discount> discounts;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ProductSubCategory))
			return false;
		ProductSubCategory other = (ProductSubCategory) o;
		return id != null && id.equals(other.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}
