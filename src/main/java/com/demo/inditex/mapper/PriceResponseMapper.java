package com.demo.inditex.mapper;


import com.demo.inditex.dtos.PriceResponseDTO;
import com.demo.inditex.entities.Prices;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@Mapper(componentModel = "spring", implementationName = "PriceResponseMapperImpl")
public interface PriceResponseMapper {

    @Mapping(source = "prices.priceList", target = "rateId")
    @Mapping(source = "rateStartDate",target = "rateStartDate")
    PriceResponseDTO mapPriceToPriceResponse(Prices prices, OffsetDateTime rateStartDate);
}
