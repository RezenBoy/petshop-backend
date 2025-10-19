package com.bowlfullbuddies.bowlfullbuddies.entity.admin;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "wishlist_item")
public class WishListItem {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        // 📝 Each item belongs to one wishlist
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "wishlist_id", nullable = false, foreignKey = @ForeignKey(name = "fk_wishlistitem_wishlist"))
        private WishList wishList;

        // 🛍️ Each item references a single product
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "fk_wishlistitem_product"))
        private Product product;

        @Column(nullable = false)
        private int quantity = 1; // default 1 for wishlist

        @Override
        public boolean equals(Object o) {
                if (this == o)
                        return true;
                if (!(o instanceof WishListItem))
                        return false;
                WishListItem other = (WishListItem) o;
                return id != null && id.equals(other.getId());
        }

        @Override
        public int hashCode() {
                return getClass().hashCode();
        }

}
