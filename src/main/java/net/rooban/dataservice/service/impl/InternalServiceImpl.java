package net.rooban.dataservice.service.impl;

import net.rooban.dataservice.dto.request.ShopperRequest;
import net.rooban.dataservice.model.Product;
import net.rooban.dataservice.model.Shopper;
import net.rooban.dataservice.model.ShopperShelf;
import net.rooban.dataservice.model.ShopperShelfId;
import net.rooban.dataservice.repository.ProductRepository;
import net.rooban.dataservice.repository.ShopperRepository;
import net.rooban.dataservice.repository.ShopperShelfRepository;
import net.rooban.dataservice.service.InternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class InternalServiceImpl implements InternalService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShopperRepository shopperRepository;
    @Autowired
    private ShopperShelfRepository ShopperShelfRepository;

    @Override
    @Transactional
    public void addMetadata(Product product) {
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void addShopperDetails(ShopperRequest shopperRequest) {
        shopperRequest.getShopperId();
        shopperRepository.save(new Shopper(shopperRequest.getShopperId()));
        List<ShopperShelf> shopperShelfList = new ArrayList<>();
        shopperRequest.getShelf().stream().forEach(shelf -> {
            shopperShelfList.add(new ShopperShelf(new ShopperShelfId(new Shopper(shopperRequest.getShopperId()), new Product(shelf.getProductId())), shelf.getRelevancyScore()));
        });
        ShopperShelfRepository.saveAll(shopperShelfList);
    }
}
