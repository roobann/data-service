package net.rooban.dataservice.dto.request;

import lombok.Data;

@Data
public class ProductMetadata {
    private String productId;
    private String category;
    private String brand;
}
