package com.demo.inditex.service;

import com.demo.inditex.entities.Prices;
import com.demo.inditex.mapper.PriceResponseMapper;
import com.demo.inditex.repositories.PricesRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.openapitools.model.PriceResponse;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PriceService {

    private final PricesRepository pricesRepository;
    private final PriceResponseMapper priceResponseMapper;

    @SneakyThrows
    public PriceResponse getPrice(Integer productId, Integer brandId, String rateStartDate){
        OffsetDateTime rateDate = parseRequestStringToDate(rateStartDate);

        List<Prices> prices = pricesRepository
                .findPricesByProductIdAndBrandIdAndDates(productId,brandId,rateDate);

        List<PriceResponse> priceResponses = prices
                .stream()
                .map(price -> priceResponseMapper.mapPriceToPriceResponse(price,rateDate))
                .collect(Collectors.toList());

        return returnPriceWithPriority(priceResponses);
    }

    private PriceResponse returnPriceWithPriority(List<PriceResponse> priceResponses) {
        PriceResponse priceResponse =  priceResponses
                .stream()
                .filter(PriceResponse::getPriority)
                .findFirst()
                .orElse(null);

        if(Objects.isNull(priceResponse) && !priceResponses.isEmpty()){
            priceResponse = priceResponses.get(0);
        }

        return priceResponse;
    }


    private OffsetDateTime parseRequestStringToDate(String rateStartDate) throws ParseException {
        DateTimeFormatter isoFormat = DateTimeFormatter.ISO_DATE_TIME;
        return OffsetDateTime.of(LocalDate.parse(rateStartDate,isoFormat),LocalTime.MAX, ZoneOffset.UTC);
    }
}
