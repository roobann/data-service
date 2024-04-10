package net.rooban.dataservice.repository;

import net.rooban.dataservice.model.ShopperShelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopperShelfRepository extends JpaRepository<ShopperShelf, Long> {
    List<ShopperShelf> findByIdShopperShopperId(String shopperId);
}
