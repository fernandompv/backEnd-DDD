package com.demo.inditex.price.application.services;

import com.demo.inditex.Codegen.dto.PriceResponseDTO;
import com.demo.inditex.price.domain.port.PriceRepository;
import com.demo.inditex.price.infraestructure.Exceptions.ParseDateException;
import com.demo.inditex.price.infraestructure.Exceptions.ResourceNotFoundException;
import com.demo.inditex.price.domain.entities.Price;
import com.demo.inditex.price.application.mapper.PriceResponseMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static com.demo.inditex.util.DateUtils.parseRequestStringToDate;

@AllArgsConstructor
@Service
public non-sealed class PricesServiceImpl implements PriceService {
    private final PriceRepository priceRepository;
    private final PriceResponseMapper priceResponseMapper;


    @Override
    public Mono<PriceResponseDTO> getPriceByPriority(Integer productId, Integer brandId, String priceStartDate) throws ResourceNotFoundException, ParseDateException {
        Flux<Price> prices = findPriceByParams(productId,brandId,priceStartDate);
        return prices
                .reduce((price1, price2) -> price1.getPriority() > price2.getPriority() ? price1:price2)
                .map(priceResponseMapper::mapPriceToPriceResponse);
    }

    @Override
    public Flux<Price> findPriceByParams(Integer productId, Integer brandId, String priceSearchDate) throws ParseDateException {
        LocalDateTime searchDate = parseRequestStringToDate(priceSearchDate);
        return priceRepository
                .findPricesByProductIdAndBrandIdAndDates(productId,brandId,searchDate);
    }


}
