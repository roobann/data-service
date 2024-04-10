package net.rooban.dataservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public Product(String productId) {
        this.productId = productId;
    }
}