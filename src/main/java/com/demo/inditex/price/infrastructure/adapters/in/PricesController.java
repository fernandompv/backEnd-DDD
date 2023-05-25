package com.demo.inditex.price.infrastructure.adapters.in;

import com.demo.inditex.Codegen.api.v1.PriceApi;
import com.demo.inditex.price.application.services.PriceService;
import com.demo.inditex.price.infrastructure.Exceptions.ParseDateException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PricesController implements PriceApi {
    private final PriceService priceService;


    @Override
    public Mono<ResponseEntity<com.demo.inditex.Codegen.dto.PriceResponseDTO>> getPrice(Integer productId,
                                                                                        Integer brandId,
                                                                                        String priceStartDate,
                                                                                        ServerWebExchange exchange) {
        try {
            return priceService.getPriceByPriority(productId,brandId,priceStartDate)
                    .map(ResponseEntity::ok)
                    .defaultIfEmpty(ResponseEntity.noContent().build());
        }
        catch (ParseDateException e){
         return Mono.error(e);
        }
    }
}
