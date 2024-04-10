package net.rooban.dataservice.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ShopperResponse {
    private String shopperId;
    private List<ProductResponse> products;
}
