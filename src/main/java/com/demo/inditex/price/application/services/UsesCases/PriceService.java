package com.demo.inditex.price.application.services.UsesCases;

import com.demo.inditex.price.infraestructure.Exceptions.ParseDateException;
import com.demo.inditex.price.infraestructure.Exceptions.ResourceNotFoundException;
import com.demo.inditex.price.infraestructure.dtos.PriceResponseDTO;

import java.util.Optional;

public interface PriceService {

    PriceResponseDTO getPrice(Long productId, Long brandId, String priceStartDate) throws ParseDateException, ResourceNotFoundException;
}
