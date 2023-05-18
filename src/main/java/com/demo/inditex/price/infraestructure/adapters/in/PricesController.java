package com.demo.inditex.price.infraestructure.adapters.in;

import com.demo.inditex.price.infraestructure.Exceptions.ParseDateException;
import com.demo.inditex.price.infraestructure.Exceptions.ResourceNotFoundException;
import com.demo.inditex.price.infraestructure.adapters.in.apis.PricesApi;
import com.demo.inditex.price.infraestructure.dtos.PriceResponseDTO;
import com.demo.inditex.price.application.services.PricesServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class PricesController implements PricesApi {
    private final PricesServiceImpl pricesServiceImpl;

    @Override
    public PriceResponseDTO getPrice(@RequestParam("productId") Long productId,
                                              @RequestParam("brandId") Long brandId,
                                              @RequestParam("rateStartDate") String rateStartDate)
            throws ParseDateException, ResourceNotFoundException {
        return pricesServiceImpl.getPrice(productId,brandId,rateStartDate);
    }
}
