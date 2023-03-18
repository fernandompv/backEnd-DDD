package com.demo.redegal.controller;

import com.demo.redegal.service.PriceServiceImpl;
import org.openapitools.api.PriceApi;
import org.openapitools.model.PriceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class PriceController implements PriceApi {

    @Autowired
    private PriceServiceImpl priceService;
    @Override
    public ResponseEntity<PriceResponse> getPrice(Integer productId, Integer brandId, String rateStartDate) {
        return ResponseEntity.ok(priceService.getPrice(productId,brandId,rateStartDate));
    }
}
