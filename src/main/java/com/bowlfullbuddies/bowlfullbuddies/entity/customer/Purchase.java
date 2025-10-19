package com.bowlfullbuddies.bowlfullbuddies.entity.customer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bowlfullbuddies.bowlfullbuddies.entity.AddressEmbeddable;
import com.bowlfullbuddies.bowlfullbuddies.enums.PaymentMode;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bill_no", nullable = false, unique = true)
    private String billNo;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] billImage;

    @Transient
    private MultipartFile billFile;

    @Column(name = "purchase_date_time", nullable = false)
    private LocalDateTime purchaseDateAndTime;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "vendor_name")
    private String vendorName;

    @Embedded
    private AddressEmbeddable addressEmbeddable;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_mode")
    private PaymentMode paymentMode;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseItem> items = new ArrayList<>();

    // Helper methods for bi-directional relationship
    public void addItem(PurchaseItem item) {
        items.add(item);
        item.setPurchase(this);
    }

    public void removeItem(PurchaseItem item) {
        items.remove(item);
        item.setPurchase(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Purchase))
            return false;
        Purchase other = (Purchase) o;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
