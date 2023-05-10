package com.demo.inditex.controllers;

import com.demo.inditex.Exceptions.ParseDateException;
import com.demo.inditex.dtos.PriceResponseDTO;
import com.demo.inditex.services.PricesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;


@RestController
@AllArgsConstructor
@RequestMapping("api/price")
public class PricesController {
    private final PricesService pricesService;
    @Operation(summary = "endpoint to Get price")
    @GetMapping(value = "/")
    public ResponseEntity<PriceResponseDTO> getPrice(@RequestParam("productId") Integer productId,
                                                     @RequestParam("brandId") Integer brandId,
                                                     @RequestParam("rateStartDate") String rateStartDate) throws ParseDateException {
        return ResponseEntity.ok(pricesService.getPrice(productId,brandId,rateStartDate));
    }
}
