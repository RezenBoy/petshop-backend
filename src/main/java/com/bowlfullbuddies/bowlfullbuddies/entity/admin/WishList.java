package com.bowlfullbuddies.bowlfullbuddies.entity.admin;

import java.util.List;

import com.bowlfullbuddies.bowlfullbuddies.entity.customer.Users;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class WishList {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Users users;
	
	@OneToMany(mappedBy = "wishList")
	List<WishListItems> wishListItems;
}
