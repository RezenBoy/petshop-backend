package com.bowlfullbuddies.bowlfullbuddies.entity.admin;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductSubCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String subCategoryName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
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
}
