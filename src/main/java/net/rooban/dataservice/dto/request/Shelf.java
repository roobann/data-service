package net.rooban.dataservice.dto.request;

import lombok.Data;

@Data
public class Shelf {
    private String productId;
    private Double relevancyScore;
}
