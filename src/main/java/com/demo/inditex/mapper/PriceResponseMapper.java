package com.demo.inditex.mapper;


import com.demo.inditex.dtos.PriceResponse;
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
    PriceResponse mapPriceToPriceResponse(Prices prices, OffsetDateTime rateStartDate);
}
