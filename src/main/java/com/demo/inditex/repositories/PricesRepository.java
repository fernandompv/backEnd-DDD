package com.demo.inditex.repositories;

import com.demo.inditex.entities.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PricesRepository extends JpaRepository<Prices,Long> {

    @Query("select a from Prices a where a.productId = :productId AND a.brandId = :brandId AND a.startDate < :rateStartDate AND a.endDate > :rateStartDate")
    Optional<List<Prices>> findPricesByProductIdAndBrandIdAndDates(@Param("productId") Integer productId, @Param("brandId") Integer brandId, @Param("rateStartDate") OffsetDateTime rateStartDate);
}
