package com.demo.inditex.price.infrastructure.adapters.out;

import com.demo.inditex.price.domain.entities.Price;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository
public interface PriceCrudRepository extends ReactiveCrudRepository<Price,Long> {
    @Query("select * from price where product_id = :productId AND brand_id = :brandId AND start_date <= :priceStartDate AND end_date >= :priceStartDate")
    Flux<Price> findPricesByProductIdAndBrandIdAndDates(Integer productId,Integer brandId, LocalDateTime priceStartDate);
}
