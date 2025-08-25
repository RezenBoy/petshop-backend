package com.bowlfullbuddies.bowlfullbuddies.entity.customer;

import com.bowlfullbuddies.bowlfullbuddies.entity.admin.Product;
import com.bowlfullbuddies.bowlfullbuddies.entity.admin.TaxPercentage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PurchaseItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Purchase purchase;
	
	@ManyToOne
	private Product product;
	
	private Integer quantity;
	
	private Double costPricePerUnit;
	
	private Double totalPriceBeforeTax;
	
	private Double taxAmount;
	
	private Double totalPriceWithTax;
	
	@ManyToOne
	private TaxPercentage taxPercentage;
}
