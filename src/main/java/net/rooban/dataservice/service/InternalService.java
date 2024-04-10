package net.rooban.dataservice.service;

import net.rooban.dataservice.dto.request.ShopperRequest;
import net.rooban.dataservice.model.Product;

public interface InternalService {
    void addMetadata(Product product);
    void addShopperDetails(ShopperRequest shopperRequest);
}
