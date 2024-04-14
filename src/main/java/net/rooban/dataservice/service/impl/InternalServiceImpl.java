package net.rooban.dataservice.service.impl;

import net.rooban.dataservice.dto.request.Shelf;
import net.rooban.dataservice.dto.request.ShopperRequest;
import net.rooban.dataservice.exception.CustomException;
import net.rooban.dataservice.model.Product;
import net.rooban.dataservice.model.Shopper;
import net.rooban.dataservice.model.ShopperShelf;
import net.rooban.dataservice.model.ShopperShelfId;
import net.rooban.dataservice.repository.ProductRepository;
import net.rooban.dataservice.repository.ShopperRepository;
import net.rooban.dataservice.repository.ShopperShelfRepository;
import net.rooban.dataservice.service.InternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public Product addMetadata(Product product) {
        try {
            productRepository.save(product);
        } catch (Exception e) {
            throw new CustomException("Error occured while adding metadata");
        }
        return product;
    }

    @Override
    @Transactional
    public ShopperRequest addShopperDetails(ShopperRequest shopperRequest) throws CustomException {
        try {
            shopperRequest.getShopperId();
            Set<ShopperShelf> shopperShelfList = new HashSet<>();
            List<String> productList = new ArrayList<>();
            shopperRequest.getShelf().stream().collect(Collectors.toSet()).forEach(shelf -> {
                shopperShelfList.add(new ShopperShelf(new ShopperShelfId(new Shopper(shopperRequest.getShopperId()), new Product(shelf.getProductId())), shelf.getRelevancyScore()));
                productList.add(shelf.getProductId());
            });
            List<Product> productListAvailable= productRepository.findAllByProductIdIn(productList);
            if(productListAvailable.size() == shopperRequest.getShelf().stream().collect(Collectors.toSet()).size())
                shopperRepository.saveAndFlush(new Shopper(shopperRequest.getShopperId(), shopperShelfList.stream().collect(Collectors.toList())));
            else
                throw new CustomException("Product Id data not available");
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        return shopperRequest;
    }

    @Override
    @Transactional
    public void deleteShopperDetails(String shopperId) {
        shopperRepository.deleteByShopperId(shopperId);
    }

    @Override
    public boolean loadData() {
        Boolean response = false;
        try {
            IntStream.rangeClosed(1, 10).forEach(j -> {
                String[] productId = {"", "PD-1000", "PD-1001", "PD-1002", "PD-1003", "PD-1004", "PD-1005", "PD-1006", "PD-1007", "PD-1008", "PD-1009", "PD-1010"};
                String[] category = {"", "Electronics", "Fashion", "Home", "Beauty", "Babies", "Tools", "Books", "Sports", "Grocery", "Furniture", "Stationary"};
                String[] brand = {"", "Samsung", "Apple", "OnePlus", "Nike", "Adidas", "Puma", "Zara", "H&M", "Biba", "Oppo"};
                productRepository.save(new Product(productId[j], category[j], brand[j]));
            });
            IntStream.rangeClosed(1, 100).forEach(i -> {
                Random random = new Random();
                String shopperId = "S-" + random.nextInt(1000, 9999);
                ShopperRequest shopperRequest = new ShopperRequest();
                shopperRequest.setShopperId(shopperId);
                List<Shelf> shopperShelfList = new ArrayList<>();
                //int max = 10; int min = 1; random.nextInt(max - min + 1) + min
                IntStream.rangeClosed(1, 10).forEach(j -> {
                    String[] productId = {"", "PD-1000", "PD-1001", "PD-1002", "PD-1003", "PD-1004", "PD-1005", "PD-1006", "PD-1007", "PD-1008", "PD-1009", "PD-1010"};
                    String[] category = {"", "Electronics", "Fashion", "Home", "Beauty", "Babies", "Tools", "Books", "Sports", "Grocery", "Furniture", "Stationary"};
                    String[] brand = {"", "Samsung", "Apple", "OnePlus", "Nike", "Adidas", "Puma", "Zara", "H&M", "Biba", "Oppo"};
                    //productRepository.save(new Product(productId[j], category[j], brand[j]));
                    Shelf shelf = new Shelf();
                    shelf.setProductId(productId[j]);
                    shelf.setRelevancyScore(random.nextDouble() * 100);
                    shopperShelfList.add(shelf);
                });
                shopperRequest.setShelf(shopperShelfList);
                addShopperDetails(shopperRequest);

            });
            response = true;
        } catch (Exception ex) {
            response = false;
        }
        return response;
    }
}
