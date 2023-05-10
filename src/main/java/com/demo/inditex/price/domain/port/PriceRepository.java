package com.demo.inditex.price.domain.port;

import com.demo.inditex.price.domain.entities.Prices;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface PriceRepository {
    List<Prices> findPricesByProductIdAndBrandIdAndDates(Integer productId, Integer brandId, OffsetDateTime rateStartDate);
}
