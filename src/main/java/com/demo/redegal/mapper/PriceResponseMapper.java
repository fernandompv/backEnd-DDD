package com.demo.redegal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.openapitools.model.PriceResponse;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface PriceResponseMapper {

    @Mapping(source = "startDate", target = "rateStartDate", qualifiedByName = "parseDate")
    @Mapping(source = "priceList", target = "rateId")
    PriceResponse mapResultSetToPriceResponse(Integer brandId, Integer productId,
                                              Integer priceList, BigDecimal price,Boolean priority,
                                              String currency, Date startDate);


    @Named("parseDate")
    default OffsetDateTime parseDate (Date startDate) {
        if(Objects.nonNull(startDate)) {
            LocalDate localDate = startDate.toLocalDate();
            return localDate.atTime(OffsetTime.now());
        }
        return null;
    }
}
