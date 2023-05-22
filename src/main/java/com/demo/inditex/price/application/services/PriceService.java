package com.demo.inditex.price.application.services;

import com.demo.inditex.price.infraestructure.Exceptions.ParseDateException;
import com.demo.inditex.price.infraestructure.Exceptions.ResourceNotFoundException;
import com.demo.inditex.price.infraestructure.dtos.PriceResponseDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public sealed interface PriceService permits PricesServiceImpl {

    Optional<PriceResponseDTO> getPrice(Long productId, Long brandId, String priceStartDate) throws ParseDateException, ResourceNotFoundException;
}
