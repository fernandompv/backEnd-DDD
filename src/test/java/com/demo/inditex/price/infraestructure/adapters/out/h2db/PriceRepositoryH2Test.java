package com.demo.inditex.price.infraestructure.adapters.out.h2db;

import com.demo.inditex.price.domain.entities.Price;
import com.demo.inditex.price.infraestructure.Exceptions.ParseDateException;
import com.demo.inditex.price.infraestructure.adapters.out.PriceCrudRepository;
import com.demo.inditex.util.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PriceRepositoryH2Test {

    @Autowired
    private PriceCrudRepository crudRepository;

    private PriceRepositoryH2 priceRepository;

    @BeforeEach
    public void init(){
        priceRepository = new PriceRepositoryH2(crudRepository);
    }
    @Test
    @Order(1)
    public void test_FindByParams_10AM() throws ParseDateException {
        //GIVEN
        LocalDateTime date = DateUtils.parseRequestStringToDate("2020-06-14T10:00:00Z");

        //WHEN
        Flux<Price> prices = priceRepository.findPricesByProductIdAndBrandIdAndDates(35455,1,date);

        //THEN
        StepVerifier.create(prices)
                .expectSubscription()
                .expectNextMatches(price -> price.getPrice().compareTo(new BigDecimal("35.5")) == 0)
                .expectComplete()
                .verify();
    }

    @Test
    @Order(2)
    public void test_FindByParams_16PM() throws ParseDateException {
        //GIVEN
        LocalDateTime date = DateUtils.parseRequestStringToDate("2020-06-14T16:00:00Z");

        //WHEN
        Flux<Price> prices = priceRepository.findPricesByProductIdAndBrandIdAndDates(35455,1,date);

        //THEN
        StepVerifier.create(prices)
                .expectSubscription()
                .expectNextMatches(price -> price.getPrice().compareTo(new BigDecimal("35.5")) == 0)
                .expectNextMatches(price -> price.getPrice().compareTo(new BigDecimal("25.45")) == 0)
                .expectComplete()
                .verify();
    }

    @Test
    @Order(3)
    public void test_FindByParams_21PM() throws ParseDateException {
        //GIVEN
        LocalDateTime date = DateUtils.parseRequestStringToDate("2020-06-14T21:00:00Z");

        //WHEN
        Flux<Price> prices = priceRepository.findPricesByProductIdAndBrandIdAndDates(35455,1,date);

        //THEN
        StepVerifier.create(prices)
                .expectSubscription()
                .expectNextMatches(price -> price.getPrice().compareTo(new BigDecimal("35.5")) == 0)
                .expectComplete()
                .verify();
    }

    @Test
    @Order(4)
    public void test_FindByParams_nextDay_10PM() throws ParseDateException {
        //GIVEN
        LocalDateTime date = DateUtils.parseRequestStringToDate("2020-06-15T10:00:00Z");

        //WHEN
        Flux<Price> prices = priceRepository.findPricesByProductIdAndBrandIdAndDates(35455,1,date);

        //THEN
        StepVerifier.create(prices)
                .expectSubscription()
                .expectNextMatches(price -> price.getPrice().compareTo(new BigDecimal("35.5")) == 0)
                .expectNextMatches(price -> price.getPrice().compareTo(new BigDecimal("30.5")) == 0)
                .expectComplete()
                .verify();
    }

    @Test
    @Order(5)
    public void test_FindByParams_OtherDaY_21PM() throws ParseDateException {
        //GIVEN
        LocalDateTime date = DateUtils.parseRequestStringToDate("2020-06-16T21:00:00Z");

        //WHEN
        Flux<Price> prices = priceRepository.findPricesByProductIdAndBrandIdAndDates(35455,1,date);

        //THEN
        StepVerifier.create(prices)
                .expectSubscription()
                .expectNextMatches(price -> price.getPrice().compareTo(new BigDecimal("35.5")) == 0)
                .expectNextMatches(price -> price.getPrice().compareTo(new BigDecimal("38.95")) == 0)
                .expectComplete()
                .verify();
    }

    @Test
    @Order(6)
    public void test_FindByParams_empty() throws ParseDateException {
        //GIVEN
        LocalDateTime date = DateUtils.parseRequestStringToDate("2023-06-16T21:00:00Z");

        //WHEN
        Flux<Price> prices = priceRepository.findPricesByProductIdAndBrandIdAndDates(35455,1,date);

        //THEN
        StepVerifier.create(prices)
                .expectComplete()
                .verify();
    }
}