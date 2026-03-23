package com.bowlfullbuddies.bowlfullbuddies.entity.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties({"subCategories", "hibernateLazyInitializer", "handler"})
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
		if (!(o instanceof ProductSubCategory other))
			return false;
		return id != null && id.equals(other.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}
