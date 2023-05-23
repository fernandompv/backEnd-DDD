package com.demo.inditex.price.application.services;

import com.demo.inditex.Codegen.dto.PriceResponseDTO;
import com.demo.inditex.price.domain.port.PriceRepository;
import com.demo.inditex.price.infraestructure.Exceptions.ParseDateException;
import com.demo.inditex.price.infraestructure.Exceptions.ResourceNotFoundException;
import com.demo.inditex.price.domain.entities.Price;
import com.demo.inditex.price.application.mapper.PriceResponseMapper;
import io.r2dbc.spi.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

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
                .reduce(this::priceComparatorForPriority)
                .map(priceResponseMapper::mapPriceToPriceResponse);
    }

    private Price priceComparatorForPriority(Price price1, Price price2){
        //Aqui le he añadido que si 2 o mas precios tienen la misma prioridad seleccione el que tenga menor precio (para favorecer al cliente)
        BiPredicate<Price, Price> firstPriceHavePriority = (firstPrice, secondPrice) ->
                firstPrice.getPriority() > secondPrice.getPriority() ||
                        firstPrice.getPriority().equals(secondPrice.getPriority()) &&
                                firstPrice.getPrice().compareTo(secondPrice.getPrice()) < 0;

        return firstPriceHavePriority.test(price1,price2) ? price1 : price2;
    }

    @Cacheable("prices")
    @Override
    public Flux<Price> findPriceByParams(Integer productId, Integer brandId, String priceSearchDate) throws ParseDateException {
        LocalDateTime searchDate = parseRequestStringToDate(priceSearchDate);
        return priceRepository
                .findPricesByProductIdAndBrandIdAndDates(productId,brandId,searchDate);
    }


}
