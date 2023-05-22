package com.demo.inditex.price.application.services;

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
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static com.demo.inditex.util.DateUtils.parseRequestStringToDate;

@AllArgsConstructor
@Service
public non-sealed class PricesServiceImpl implements PriceService {
    private final PriceRepository pricesRepository;
    private final PriceResponseMapper priceResponseMapper;


    @Override
    public Optional<PriceResponseDTO> getPrice(Long productId, Long brandId, String priceStartDate) throws ParseDateException, ResourceNotFoundException {
        OffsetDateTime priceSearchDate = parseRequestStringToDate(priceStartDate);

        List<Price> prices = pricesRepository
                .findPricesByProductIdAndBrandIdAndDates(productId,brandId,priceSearchDate);

        return prices
                .stream()
                .max(Comparator.comparingLong(Price::getPriority))
                .map(priceResponseMapper::mapPriceToPriceResponse);
    }
}
