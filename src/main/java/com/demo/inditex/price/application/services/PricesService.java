package com.demo.inditex.price.application.services;

import com.demo.inditex.price.domain.port.PriceRepository;
import com.demo.inditex.price.infraestructure.Exceptions.ParseDateException;
import com.demo.inditex.price.infraestructure.dtos.PriceResponseDTO;
import com.demo.inditex.price.domain.entities.Prices;
import com.demo.inditex.price.application.mapper.PriceResponseMapper;
import com.demo.inditex.util.ErrorDictionary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PricesService {

    private final PriceRepository pricesRepository;
    private final PriceResponseMapper priceResponseMapper;


    public PriceResponseDTO getPrice(Integer productId, Integer brandId, String rateStartDate) throws ParseDateException {
        OffsetDateTime rateDate = parseRequestStringToDate(rateStartDate);

        List<Prices> prices = pricesRepository
                .findPricesByProductIdAndBrandIdAndDates(productId,brandId,rateDate);

        List<PriceResponseDTO> priceResponsDTOS = prices
                .stream()
                .map(price -> priceResponseMapper.mapPriceToPriceResponse(price,rateDate))
                .collect(Collectors.toList());

        return returnPriceWithPriority(priceResponsDTOS);
    }

    private PriceResponseDTO returnPriceWithPriority(List<PriceResponseDTO> priceResponsDTOS) {
        PriceResponseDTO priceResponseDTO =  priceResponsDTOS
                .stream()
                .filter(PriceResponseDTO::priority)
                .findFirst()
                .orElse(null);

        if(Objects.isNull(priceResponseDTO) && !priceResponsDTOS.isEmpty()){
            priceResponseDTO = priceResponsDTOS.get(0);
        }

        return priceResponseDTO;
    }


    private OffsetDateTime parseRequestStringToDate(String rateStartDate) throws ParseDateException {
        try {
            DateTimeFormatter isoFormat = DateTimeFormatter.ISO_DATE_TIME;
            return OffsetDateTime.of(LocalDate.parse(rateStartDate,isoFormat),LocalTime.MAX, ZoneOffset.UTC);
        }catch (Exception e){
            throw new ParseDateException(String.format(ErrorDictionary.PARSE_DATE_ERROR_MESSAGE,rateStartDate));
        }

    }
}
