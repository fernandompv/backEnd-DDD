package com.demo.inditex.price.infrastructure.adapters.out.h2db;

import com.demo.inditex.price.domain.entities.Price;
import com.demo.inditex.price.domain.port.PriceRepository;
import com.demo.inditex.price.infrastructure.adapters.out.PriceCrudRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository
@AllArgsConstructor
public class PriceRepositoryH2 implements PriceRepository {

    private final PriceCrudRepository crudRepository;
    @Override
    public Flux<Price> findPricesByProductIdAndBrandIdAndDates(Integer productId, Integer brandId, LocalDateTime priceStartDate) {
        return crudRepository.findPricesByProductIdAndBrandIdAndDates(productId,brandId,priceStartDate)
                .onErrorMap(IllegalArgumentException::new);
    }
}
