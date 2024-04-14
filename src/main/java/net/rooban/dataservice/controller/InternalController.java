package net.rooban.dataservice.controller;

import net.rooban.dataservice.dto.request.ShopperRequest;
import net.rooban.dataservice.model.Product;
import net.rooban.dataservice.model.Shopper;
import net.rooban.dataservice.service.InternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal")
public class InternalController {

    @Autowired
    private InternalService internalService;

    @PostMapping("/product/metadata")
    public ResponseEntity<Product> metadata(@RequestBody Product product) {
        return ResponseEntity.ok(internalService.addMetadata(product));
    }

    @PostMapping("/shopper/metadata")
    public ResponseEntity<ShopperRequest> metadata(@RequestBody ShopperRequest shopperRequest) {
            return ResponseEntity.ok(internalService.addShopperDetails(shopperRequest));
    }

    @GetMapping("/data")
    public ResponseEntity<String> loadData() {
        if(internalService.loadData())
            return ResponseEntity.ok("Data loaded successfully");
        else
            return ResponseEntity.ok("Data load failed");
    }

    @DeleteMapping("/shopper/{shopperId}")
    public void deleteShopper(@PathVariable String shopperId) {
        internalService.deleteShopperDetails(shopperId);
    }
}
