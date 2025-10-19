package com.bowlfullbuddies.bowlfullbuddies.entity.admin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import com.bowlfullbuddies.bowlfullbuddies.entity.AddressEmbeddable;
import com.bowlfullbuddies.bowlfullbuddies.entity.customer.Users;
import com.bowlfullbuddies.bowlfullbuddies.enums.OrderStatus;
import com.bowlfullbuddies.bowlfullbuddies.enums.PaymentMode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private AddressEmbeddable addressEmbeddable;

	@Column(name = "order_date_time", nullable = false)
	private LocalDateTime orderDateTime;

	@ManyToOne
	private Users users;

	@Enumerated(EnumType.STRING)
	private PaymentMode paymentMode;

	private Double totalPrice;

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	@Lob
	private String orderCancelReason;

	@OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
	List<OrderItemList> orderItemLists = new ArrayList<OrderItemList>();

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Orders))
			return false;
		Orders other = (Orders) o;
		return id != null && id.equals(other.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}
