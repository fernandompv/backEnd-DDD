package com.demo.inditex.price.infraestructure.adapters.in;

import com.demo.inditex.price.application.services.PriceService;
import com.demo.inditex.price.infraestructure.Exceptions.ParseDateException;
import com.demo.inditex.price.infraestructure.Exceptions.ResourceNotFoundException;
import com.demo.inditex.price.infraestructure.dtos.PriceResponseDTO;
import com.demo.inditex.price.application.services.PricesServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public non-sealed class PricesController implements PricesApi {
    private final PriceService priceService;

    @Override
    public ResponseEntity<Optional<PriceResponseDTO>> getPrice(@RequestParam("productId") Long productId,
                                                    @RequestParam("brandId") Long brandId,
                                                    @RequestParam("priceStartDate") String priceStartDate)
            throws ParseDateException, ResourceNotFoundException {
        Optional<PriceResponseDTO> result = priceService.getPrice(productId,brandId,priceStartDate);

        if(result.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(result);
    }
}
