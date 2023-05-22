package com.demo.inditex.price.infraestructure.adapters.out.h2db;

import com.demo.inditex.price.domain.entities.Price;
import com.demo.inditex.price.domain.port.PriceRepository;
import com.demo.inditex.price.infraestructure.Exceptions.ResourceNotFoundException;
import com.demo.inditex.price.infraestructure.adapters.out.PriceCrudRepository;
import com.demo.inditex.util.ErrorDictionary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class PriceRepositoryH2 implements PriceRepository {

    private final PriceCrudRepository crudRepository;
    @Override
    public List<Price> findPricesByProductIdAndBrandIdAndDates(Long productId, Long brandId, OffsetDateTime priceStartDate) {
        return crudRepository.findPricesByProductIdAndBrandIdAndDates(productId,brandId,priceStartDate)
                .orElseThrow(IllegalArgumentException::new);
    }
}
