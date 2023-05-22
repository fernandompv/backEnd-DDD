package com.demo.inditex.price.application.services;

import com.demo.inditex.Codegen.dto.PriceResponseDTO;
import com.demo.inditex.price.application.mapper.PriceResponseMapper;
import com.demo.inditex.price.domain.entities.Price;
import com.demo.inditex.price.domain.port.PriceRepository;
import com.demo.inditex.price.infraestructure.Exceptions.ParseDateException;
import com.demo.inditex.util.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@Tag("UnitTes")
public class PriceServiceTest {

    @InjectMocks
    private PricesServiceImpl priceService;

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceResponseMapper priceResponseMapper;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

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

        PriceResponseDTO priceResponseDTO = PriceResponseDTO
                .builder()
                .price(new BigDecimal("50.60"))
                .priority(1)
                .rateId(1L)
                .currency("EUR")
                .build();

        Flux<Price> pricesFromDb = Flux.just(price);

        //WHEN
        when(priceRepository.findPricesByProductIdAndBrandIdAndDates(anyInt(),anyInt(),any(LocalDateTime.class)))
                .thenReturn(pricesFromDb);
        when(priceResponseMapper.mapPriceToPriceResponse(price))
                .thenReturn(priceResponseDTO);

        Mono<PriceResponseDTO> priceResponse = priceService.getPriceByPriority(35455,1,searchDate);

        //THEN
        StepVerifier.create(priceResponse)
                .expectSubscription()
                .expectNextMatches(priceResponseResult -> priceResponseResult.equals(priceResponseDTO))
                .expectComplete()
                .verify();
    }

    @Test
    public void getPrice_different_priority() throws ParseDateException {
        //GIVEN
        LocalDateTime startDate = DateUtils.parseRequestStringToDate("2020-06-14T10:00:00");
        LocalDateTime endDate = DateUtils.parseRequestStringToDate("2020-12-31T23:59:59");
        String searchDate = "2020-06-17T10:00:00";

        Price price = new Price();
        price.setPriority(0L);
        price.setPrice(new BigDecimal("50.60"));
        price.setBrandId(1L);
        price.setProductId(35455L);
        price.setStartDate(startDate);
        price.setEndDate(endDate);

        Price price2 = new Price();
        price2.setPriority(1L);
        price2.setPrice(new BigDecimal("200.60"));
        price2.setBrandId(1L);
        price2.setProductId(35455L);
        price2.setStartDate(startDate);
        price2.setEndDate(endDate);

        PriceResponseDTO priceResponseDTO = PriceResponseDTO
                .builder()
                .price(new BigDecimal("200.60"))
                .priority(1)
                .rateId(1L)
                .currency("EUR")
                .build();

        Flux<Price> pricesFromDb = Flux.fromIterable(Arrays.asList(price,price2));

        //WHEN
        when(priceRepository.findPricesByProductIdAndBrandIdAndDates(anyInt(),anyInt(),any(LocalDateTime.class)))
                .thenReturn(pricesFromDb);
        when(priceResponseMapper.mapPriceToPriceResponse(price2))
                .thenReturn(priceResponseDTO);

        Mono<PriceResponseDTO> priceResponse = priceService.getPriceByPriority(35455,1,searchDate);

        //THEN
        StepVerifier.create(priceResponse)
                .expectSubscription()
                .expectNextMatches(priceResponseResult -> priceResponseResult.equals(priceResponseDTO))
                .expectComplete()
                .verify();
    }

    @Test
    public void getPrice_same_priority() throws ParseDateException {
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

        Price price2 = new Price();
        price2.setPriority(1L);
        price2.setPrice(new BigDecimal("200.60"));
        price2.setBrandId(1L);
        price2.setProductId(35455L);
        price2.setStartDate(startDate);
        price2.setEndDate(endDate);

        Price price3 = new Price();
        price3.setPriority(1L);
        price3.setPrice(new BigDecimal("10.60"));
        price3.setBrandId(1L);
        price3.setProductId(35455L);
        price3.setStartDate(startDate);
        price3.setEndDate(endDate);

        PriceResponseDTO priceResponseDTO = PriceResponseDTO
                .builder()
                .price(new BigDecimal("50.60"))
                .priority(1)
                .rateId(1L)
                .currency("EUR")
                .build();

        Flux<Price> pricesFromDb = Flux.fromIterable(Arrays.asList(price,price2,price3));

        //WHEN
        when(priceRepository.findPricesByProductIdAndBrandIdAndDates(anyInt(),anyInt(),any(LocalDateTime.class)))
                .thenReturn(pricesFromDb);
        when(priceResponseMapper.mapPriceToPriceResponse(price3))
                .thenReturn(priceResponseDTO);

        Mono<PriceResponseDTO> priceResponse = priceService.getPriceByPriority(35455,1,searchDate);

        //THEN
        StepVerifier.create(priceResponse)
                .expectSubscription()
                .expectNextMatches(priceResponseResult -> priceResponseResult.equals(priceResponseDTO))
                .expectComplete()
                .verify();
    }
}