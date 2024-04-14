package net.rooban.dataservice.repository;

import net.rooban.dataservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductId(String s);

    List<Product> findAllByProductIdIn(List<String> productId);

    void deleteByProductId(String productId);
}
