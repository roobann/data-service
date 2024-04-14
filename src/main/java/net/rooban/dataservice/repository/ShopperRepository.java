package net.rooban.dataservice.repository;

import net.rooban.dataservice.model.Shopper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopperRepository extends JpaRepository<Shopper, Long> {
    Shopper findByShopperId(String s);

    void deleteByShopperId(String shopperId);
}
