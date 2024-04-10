package net.rooban.dataservice.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ShopperRequest {
    private String shopperId;
    private List<Shelf> shelf;
}
