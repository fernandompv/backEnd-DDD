package com.demo.inditex.price.infraestructure.adapters.in.apis;

import com.demo.inditex.price.infraestructure.Exceptions.ParseDateException;
import com.demo.inditex.price.infraestructure.Exceptions.ResourceNotFoundException;
import com.demo.inditex.price.infraestructure.dtos.PriceResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/price")
public interface PricesApi {

    @Operation(summary = "Endpoint to Get price by params")
    @GetMapping()
    public PriceResponseDTO getPrice(Long productId, Long brandId, String rateStartDate) throws ParseDateException, ResourceNotFoundException;
}
