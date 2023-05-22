package com.demo.inditex.price.infraestructure.dtos;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record PriceResponseDTO(Long productId,Long brandId,Long rateId,OffsetDateTime priceStartDate,OffsetDateTime priceEndDate, BigDecimal price, Long priority) {}
