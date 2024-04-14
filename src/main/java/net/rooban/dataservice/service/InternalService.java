package net.rooban.dataservice.service;

import net.rooban.dataservice.dto.request.ShopperRequest;
import net.rooban.dataservice.model.Product;
import net.rooban.dataservice.model.Shopper;

public interface InternalService {
    Product addMetadata(Product product);
    ShopperRequest addShopperDetails(ShopperRequest shopperRequest);

    boolean loadData();

    void deleteShopperDetails(String shopperId);

    void deleteProductDetails(String productId);
}
