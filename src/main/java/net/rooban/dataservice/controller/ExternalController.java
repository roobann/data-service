package net.rooban.dataservice.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import net.rooban.dataservice.dto.response.ShopperResponse;
import net.rooban.dataservice.service.ExternalService;
import net.rooban.dataservice.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/external")
@Validated
public class ExternalController {

    @Autowired
    private ExternalService externalService;


    @GetMapping("/products/{shopperId}")
    public ResponseEntity<ShopperResponse> getProductsByShopper(@PathVariable @NotEmpty @NotBlank(message = Constants.SHOPPER_ID_VALIDATION) String shopperId,
                                                                @RequestParam(value = "category", required = false) @Pattern(regexp = "^[a-zA-Z]*$", message = Constants.CATEGORY_VALIDATION) String category,
                                                                @RequestParam(value = "brand", required = false) @Pattern(regexp = "^[a-zA-Z]*$", message = Constants.BRAND_VALIDATION) String brand,
                                                                @RequestParam(value = "limit", required = false, defaultValue = Constants.LIMIT_DEFAULT) @Pattern(regexp = "^(0|[1-9][0-9]*)$", message = Constants.LIMIT_VALIDATION) String limit) {
        return ResponseEntity.ok(externalService.getProductsByShopper(shopperId, category, brand, Integer.parseInt(limit)));
    }

}
