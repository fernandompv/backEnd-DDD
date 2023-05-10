package com.demo.inditex.price.infraestructure.adapters.out;

import com.demo.inditex.price.domain.entities.Prices;
import com.demo.inditex.price.domain.port.PriceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
@Repository
@AllArgsConstructor
public class PriceRepositoryH2 implements PriceRepository {

    private final PriceCrudRepository crudRepository;
    @Override
    public List<Prices> findPricesByProductIdAndBrandIdAndDates(Integer productId, Integer brandId, OffsetDateTime rateStartDate) {
        return crudRepository.findPricesByProductIdAndBrandIdAndDates(productId,brandId,rateStartDate)
                .orElseThrow(IllegalArgumentException::new);
    }
}
