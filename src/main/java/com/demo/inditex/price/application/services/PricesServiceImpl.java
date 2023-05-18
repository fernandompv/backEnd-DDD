package com.demo.inditex.price.application.services;

import com.demo.inditex.price.application.services.UsesCases.PriceService;
import com.demo.inditex.price.domain.port.PriceRepository;
import com.demo.inditex.price.infraestructure.Exceptions.ParseDateException;
import com.demo.inditex.price.infraestructure.Exceptions.ResourceNotFoundException;
import com.demo.inditex.price.infraestructure.dtos.PriceResponseDTO;
import com.demo.inditex.price.domain.entities.Price;
import com.demo.inditex.price.application.mapper.PriceResponseMapper;
import com.demo.inditex.util.ErrorDictionary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PricesServiceImpl implements PriceService {

    private final PriceRepository pricesRepository;
    private final PriceResponseMapper priceResponseMapper;


    @Override
    public PriceResponseDTO getPrice(Long productId, Long brandId, String priceStartDate) throws ParseDateException, ResourceNotFoundException {
        OffsetDateTime priceSearchDate = parseRequestStringToDate(priceStartDate);

        List<Price> prices = pricesRepository
                .findPricesByProductIdAndBrandIdAndDates(productId,brandId,priceSearchDate);

        Optional<PriceResponseDTO> priceResponseDTO = prices
                .stream()
                .max(Comparator.comparingLong(Price::getPriority))
                .map(priceResponseMapper::mapPriceToPriceResponse);


        return priceResponseDTO.orElseThrow(() -> new ResourceNotFoundException(ErrorDictionary.RESOURCE_NOT_FOUND_MESSAGE));
    }

    private OffsetDateTime parseRequestStringToDate(String date) throws ParseDateException {
        try {
            DateTimeFormatter isoFormat = DateTimeFormatter.ISO_DATE_TIME;
            return OffsetDateTime.parse(date,isoFormat);
        }catch (Exception e){
            throw new ParseDateException(String.format(ErrorDictionary.PARSE_DATE_ERROR_MESSAGE, date));
        }

    }
}
