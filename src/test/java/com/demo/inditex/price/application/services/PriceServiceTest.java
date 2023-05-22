package com.demo.inditex.price.application.services;

import com.demo.inditex.Codegen.dto.PriceResponseDTO;
import com.demo.inditex.price.domain.entities.Price;
import com.demo.inditex.price.domain.port.PriceRepository;
import com.demo.inditex.price.infraestructure.Exceptions.ParseDateException;
import com.demo.inditex.util.DateUtils;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@Tag("UnitTes")
public class PriceServiceTest {

    @InjectMocks
    private PriceService priceService;

    @Mock
    private PriceRepository priceRepository;

    @Test
    public void getPrice_byPriority() throws ParseDateException {
        //GIVEN
        LocalDateTime startDate = DateUtils.parseRequestStringToDate("2020-06-14T10:00:00");
        LocalDateTime endDate = DateUtils.parseRequestStringToDate("2020-12-31T23:59:59");
        String searchDate = "2020-06-17T10:00:00";


        Price price = new Price();
        price.setPriority(1L);
        price.setPrice(new BigDecimal("50.60"));
        price.setBrandId(1L);
        price.setProductId(35455L);
        price.setStartDate(startDate);
        price.setEndDate(endDate);

        Flux<Price> pricesFromDb = Flux.just(price);

        //WHEN
        when(priceRepository.findPricesByProductIdAndBrandIdAndDates(anyInt(),anyInt(),any(LocalDateTime.class)))
                .thenReturn(pricesFromDb);

        Mono<PriceResponseDTO> priceResponse = priceService.getPriceByPriority(35455,1,searchDate);

        //THEN
        StepVerifier.create(priceResponse)
                .expectSubscription()
                .expectComplete()
                .verify();
    }
}