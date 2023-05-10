package com.demo.inditex.dtos;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record PriceResponseDTO(OffsetDateTime rateStartDate, Long brandId,
                               Long productId, Long rateId, BigDecimal price,
                               Boolean priority, String currency) {}
