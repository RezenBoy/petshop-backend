package com.bowlfullbuddies.bowlfullbuddies.entity.admin;

import java.util.ArrayList;
import java.util.List;

import com.bowlfullbuddies.bowlfullbuddies.entity.customer.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "wishlist")
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🧍 Each user has exactly one wishlist
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "fk_wishlist_user"))
    private Users users;

    // 🛒 One wishlist can have many items
    @OneToMany(mappedBy = "wishList", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<WishListItem> wishListItems = new ArrayList<>();

    // Helper methods for bi-directional consistency
    public void addItem(WishListItem item) {
        wishListItems.add(item);
        item.setWishList(this);
    }

    public void removeItem(WishListItem item) {
        wishListItems.remove(item);
        item.setWishList(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof WishList))
            return false;
        WishList other = (WishList) o;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
