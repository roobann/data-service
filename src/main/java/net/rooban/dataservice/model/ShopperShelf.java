package net.rooban.dataservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "shopper_shelf")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopperShelf {
    @EmbeddedId
    private ShopperShelfId id;

    @Column(name = "relevancy_score")
    private Double relevancyScore;

    @Column
    private Date createdAt = new Date();

    public ShopperShelf(ShopperShelfId id, Double relevancyScore) {
        this.id = id;
        this.relevancyScore = relevancyScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopperShelf that = (ShopperShelf) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ShopperShelf{" +
                "productId=" + id.getProduct().getProductId() +
                "productCategory=" + id.getProduct().getCategory() +
                "productBrand=" + id.getProduct().getBrand() +
                ", relevancyScore=" + relevancyScore +
                '}';
    }
}
