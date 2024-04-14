package net.rooban.dataservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @Column(name = "product_id")
    private String productId;
    @Column(nullable = true)
    private String category;
    @Column(nullable = true)
    private String brand;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", updatable = false)
    private List<ShopperShelf> shopperShelves;

    public Product(String productId) {
        this.productId = productId;
    }

    public Product(String productId, String category, String brand) {
        this.productId = productId;
        this.category = category;
        this.brand = brand;
    }
}