package com.demo.inditex.price.application.services;

import com.demo.inditex.price.domain.port.PriceRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

@Tag("UnitTes")
public class PriceServiceTest {

    @InjectMocks
    private PriceService priceService;

    @Mock
    private PriceRepository priceRepository;

    public void getPrice() {
        //Aui deberian ir los test unitarios pero para la prueba no son necesarios
    }
}