package com.demo.inditex.controllers;

import com.demo.inditex.dtos.PriceResponse;
import com.demo.inditex.services.PricesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class PricesController {
    private final PricesService pricesService;
    @Operation(summary = "endpoint to Get price")
    @GetMapping(value = "api/price")
    public ResponseEntity<PriceResponse> getPrice(@RequestParam("productId") Integer productId,
                                                  @RequestParam("brandId") Integer brandId,
                                                  @RequestParam("rateStartDate") String rateStartDate) {
        return ResponseEntity.ok(pricesService.getPrice(productId,brandId,rateStartDate));
    }
}
