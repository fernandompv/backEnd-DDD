package com.demo.inditex.price.infraestructure.adapters.out.h2db;

import com.demo.inditex.price.domain.entities.Price;
import com.demo.inditex.price.infraestructure.Exceptions.ParseDateException;
import com.demo.inditex.price.infraestructure.adapters.out.PriceCrudRepository;
import com.demo.inditex.util.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PriceRepositoryH2Test {

    @Autowired
    private PriceCrudRepository crudRepository;

    private PriceRepositoryH2 priceRepository;

    @BeforeEach
    public void init(){
        priceRepository = new PriceRepositoryH2(crudRepository);
    }
    @Test
    public void test_FindByParams_10AM() throws ParseDateException {
        //GIVEN
        OffsetDateTime date = DateUtils.parseRequestStringToDate("2020-06-14T10:00:00Z");

        //WHEN
        List<Price> prices = priceRepository.findPricesByProductIdAndBrandIdAndDates(35455L,1L,date);

        //THEN
        Assertions.assertNotNull(prices);
        Assertions.assertEquals(prices.size(),1);
        Assertions.assertEquals(prices.get(0).getPrice(), new BigDecimal("35.5"));
    }

    @Test
    public void test_FindByParams_16PM() throws ParseDateException {
        //GIVEN
        OffsetDateTime date = DateUtils.parseRequestStringToDate("2020-06-14T16:00:00Z");

        //WHEN
        List<Price> prices = priceRepository.findPricesByProductIdAndBrandIdAndDates(35455L,1L,date);

        //THEN
        Assertions.assertNotNull(prices);
        Assertions.assertEquals(prices.size(),2);
        Assertions.assertEquals(prices.get(0).getPrice(), new BigDecimal("35.5"));
        Assertions.assertEquals(prices.get(1).getPrice(), new BigDecimal("25.45"));
    }

    @Test
    public void test_FindByParams_21PM() throws ParseDateException {
        //GIVEN
        OffsetDateTime date = DateUtils.parseRequestStringToDate("2020-06-14T21:00:00Z");

        //WHEN
        List<Price> prices = priceRepository.findPricesByProductIdAndBrandIdAndDates(35455L,1L,date);

        //THEN
        Assertions.assertNotNull(prices);
        Assertions.assertEquals(prices.size(),1);
        Assertions.assertEquals(prices.get(0).getPrice(), new BigDecimal("35.5"));
    }

    @Test
    public void test_FindByParams_nextDay_10PM() throws ParseDateException {
        //GIVEN
        OffsetDateTime date = DateUtils.parseRequestStringToDate("2020-06-15T10:00:00Z");

        //WHEN
        List<Price> prices = priceRepository.findPricesByProductIdAndBrandIdAndDates(35455L,1L,date);

        //THEN
        Assertions.assertNotNull(prices);
        Assertions.assertEquals(prices.size(),2);
        Assertions.assertEquals(prices.get(0).getPrice(), new BigDecimal("35.5"));
        Assertions.assertEquals(prices.get(1).getPrice(), new BigDecimal("30.5"));
    }

    @Test
    public void test_FindByParams_OtherDaY_21PM() throws ParseDateException {
        //GIVEN
        OffsetDateTime date = DateUtils.parseRequestStringToDate("2020-06-16T21:00:00Z");

        //WHEN
        List<Price> prices = priceRepository.findPricesByProductIdAndBrandIdAndDates(35455L,1L,date);

        //THEN
        Assertions.assertNotNull(prices);
        Assertions.assertEquals(prices.size(),2);
        Assertions.assertEquals(prices.get(0).getPrice(), new BigDecimal("35.5"));
        Assertions.assertEquals(prices.get(1).getPrice(), new BigDecimal("38.95"));
    }

    @Test
    public void test_FindByParams_empty() throws ParseDateException {
        //GIVEN
        OffsetDateTime date = DateUtils.parseRequestStringToDate("2023-06-16T21:00:00Z");

        //WHEN
        List<Price> prices = priceRepository.findPricesByProductIdAndBrandIdAndDates(null,1L,date);

        //THEN
        Assertions.assertNotNull(prices);
        Assertions.assertEquals(prices.size(),0);
    }

    @Test
    public void test_FindByParams_Error() throws ParseDateException {
        //GIVEN
        OffsetDateTime date = DateUtils.parseRequestStringToDate("2023-06-16T21:00:00Z");
        RuntimeException exception = assertThrows(IllegalArgumentException.class, () -> {
            priceRepository.findPricesByProductIdAndBrandIdAndDates(null,2L,date);
        });

        //THEN
        Assertions.assertNotNull(exception.getMessage());
    }
}