package com.demo.inditex.price.application.mapper;


import com.demo.inditex.Codegen.dto.PriceResponseDTO;
import com.demo.inditex.price.domain.entities.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@Mapper(componentModel = "spring", implementationName = "PriceResponseMapperImpl")
public interface PriceResponseMapper {

    @Mapping(source = "price.priceList", target = "rateId")
    PriceResponseDTO mapPriceToPriceResponse(Price price);
}
