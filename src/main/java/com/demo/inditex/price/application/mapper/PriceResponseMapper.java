package com.demo.inditex.price.application.mapper;


import com.demo.inditex.Codegen.dto.PriceResponseDTO;
import com.demo.inditex.price.domain.entities.Price;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Component
@Mapper(componentModel = "spring", implementationName = "PriceResponseMapperImpl")
public interface PriceResponseMapper {

    @Mapping(source = "price.priceList", target = "rateId")
    @Mapping(source = "price.startDate", target = "priceStartDate",qualifiedByName = "mapOffSetDateTime")
    PriceResponseDTO mapPriceToPriceResponse(Price price);

    @Named("mapOffSetDateTime")
    default OffsetDateTime mapOffSetDateTime(LocalDateTime startDate){
        ZoneId zoneId = ZoneId.of("Europe/Madrid");
        ZoneOffset offset = zoneId.getRules().getOffset(startDate);
        return startDate.atOffset(offset);
    }
}
