package com.demo.inditex.price.domain.port;

import com.demo.inditex.price.domain.entities.Price;
import org.springframework.data.repository.NoRepositoryBean;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@NoRepositoryBean
public interface PriceRepository {
    Flux<Price> findPricesByProductIdAndBrandIdAndDates(Integer productId, Integer brandId, LocalDateTime priceStartDate);
}
