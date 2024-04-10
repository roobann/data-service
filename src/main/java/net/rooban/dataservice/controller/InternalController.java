package net.rooban.dataservice.controller;

import net.rooban.dataservice.dto.request.ShopperRequest;
import net.rooban.dataservice.model.Product;
import net.rooban.dataservice.service.InternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal")
public class InternalController {

    @Autowired
    private InternalService internalService;

    @PostMapping("/product/metadata")
    public ResponseEntity<String> metadata(@RequestBody Product product) {
        internalService.addMetadata(product);
        return ResponseEntity.ok("metadata");
    }

    @PostMapping("/shopper/metadata")
    public ResponseEntity<String> metadata(@RequestBody ShopperRequest shopperRequest) {
        internalService.addShopperDetails(shopperRequest);
        return ResponseEntity.ok("shopping data");
    }
}
