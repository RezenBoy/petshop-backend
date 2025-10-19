package com.bowlfullbuddies.bowlfullbuddies.entity.customer;

import com.bowlfullbuddies.bowlfullbuddies.entity.admin.Product;
import com.bowlfullbuddies.bowlfullbuddies.entity.admin.Tax;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "purchase_item")
public class PurchaseItem {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        // 🧾 Relation to Purchase (Many items can belong to one Purchase)
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "purchase_id", nullable = false, foreignKey = @ForeignKey(name = "fk_purchaseitem_purchase"))
        private Purchase purchase;

        // 📦 Relation to Product
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "fk_purchaseitem_product"))
        private Product product;

        // 🧮 Quantity
        @Column(nullable = false)
        private Integer quantity;

        // 💰 Price fields
        @Column(name = "cost_price_per_unit", nullable = false)
        private Double costPricePerUnit;

        @Column(name = "total_price_before_tax", nullable = false)
        private Double totalPriceBeforeTax;

        @Column(name = "tax_amount", nullable = false)
        private Double taxAmount;

        @Column(name = "total_price_with_tax", nullable = false)
        private Double totalPriceWithTax;

        // 💸 Tax relation
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "tax_id", foreignKey = @ForeignKey(name = "fk_purchaseitem_tax"))
        private Tax tax;

        @Override
        public boolean equals(Object o) {
                if (this == o)
                        return true;
                if (!(o instanceof PurchaseItem))
                        return false;
                PurchaseItem other = (PurchaseItem) o;
                return id != null && id.equals(other.getId());
        }

        @Override
        public int hashCode() {
                return getClass().hashCode();
        }

}
