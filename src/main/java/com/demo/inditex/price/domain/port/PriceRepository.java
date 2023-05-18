package com.demo.inditex.price.domain.port;

import com.demo.inditex.price.domain.entities.Price;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.OffsetDateTime;
import java.util.List;

@NoRepositoryBean
public interface PriceRepository {
    List<Price> findPricesByProductIdAndBrandIdAndDates(Long productId, Long brandId, OffsetDateTime rateStartDate);
}
