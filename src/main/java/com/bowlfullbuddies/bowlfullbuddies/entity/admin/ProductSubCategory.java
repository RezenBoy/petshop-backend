package com.bowlfullbuddies.bowlfullbuddies.entity.admin;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
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

	@ManyToOne
	private ProductCategory productCategory;

	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] image;

	@Transient
	private MultipartFile file;

	@OneToMany(mappedBy = "productSubCategory", cascade = CascadeType.ALL)
	private List<Discount> discounts;
}
