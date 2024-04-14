package net.rooban.dataservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopperShelfId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "shopper_id")
    private Shopper shopper;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShopperShelfId that = (ShopperShelfId) o;

        if (!shopper.equals(that.shopper)) return false;
        return product.equals(that.product);
    }

    @Override
    public int hashCode() {
        int result = shopper.hashCode();
        result = 31 * result + product.hashCode();
        return result;
    }
}
