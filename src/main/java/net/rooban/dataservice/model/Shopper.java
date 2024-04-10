package net.rooban.dataservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "shopper")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shopper {

    @Id
    @Column(name = "shopper_id")
    private String shopperId;

    @OneToMany
    @JoinColumn(name = "shopper_id")
    private List<ShopperShelf> shopperShelves;

    public Shopper(String shopperId) {
        this.shopperId = shopperId;
    }

    @Override
    public String toString() {

        return "Shopper{" +
                "shopperId='" + shopperId + '\'' +
                ", shopperShelves=" + shopperShelves +
                '}';
    }
}