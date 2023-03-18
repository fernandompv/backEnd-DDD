package com.demo.redegal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.openapitools.model.PriceResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

@Component
@Mapper(componentModel = "spring")
public interface PriceResponseMapper {

    @Mapping(source = "startDate", target = "rateStartDate", qualifiedByName = "parseDate")
    @Mapping(source = "priceList", target = "rateId")
    PriceResponse mapResultSetToPriceResponse(Integer brandId, Integer productId,
                                              Integer priceList, BigDecimal price, Boolean priority,
                                              String currency, Timestamp startDate);


    @Named("parseDate")
    default OffsetDateTime parseDate (Timestamp startDate) {
        if(Objects.nonNull(startDate)) {
            return startDate
                    .toInstant()
                    .atOffset(ZoneOffset.UTC);
        }
        return null;
    }
}
