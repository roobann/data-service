package net.rooban.dataservice.service;


import net.rooban.dataservice.dto.response.ShopperResponse;

public interface ExternalService {
    ShopperResponse getProductsByShopper(String shopperId, String category, String brand, Integer limit);

}
