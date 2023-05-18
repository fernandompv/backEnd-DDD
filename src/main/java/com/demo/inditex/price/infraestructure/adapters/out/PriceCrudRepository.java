package com.demo.inditex.price.infraestructure.adapters.out;

import com.demo.inditex.price.domain.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface PriceCrudRepository extends JpaRepository<Price,Long> {

    @Query("select a from Price a where a.productId = :productId AND a.brand.Id = :brandId AND a.startDate <= :priceStartDate AND a.endDate >= :priceStartDate")
    Optional<List<Price>> findPricesByProductIdAndBrandIdAndDates(@Param("productId") Long productId, @Param("brandId") Long brandId, @Param("priceStartDate") OffsetDateTime priceStartDate);
}
