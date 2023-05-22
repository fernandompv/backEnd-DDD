package com.demo.inditex.price.application.services;

import com.demo.inditex.Codegen.dto.PriceResponseDTO;
import com.demo.inditex.price.domain.entities.Price;
import com.demo.inditex.price.infraestructure.Exceptions.ParseDateException;
import com.demo.inditex.price.infraestructure.Exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public sealed interface PriceService permits PricesServiceImpl {

    Mono<PriceResponseDTO> getPriceByPriority(Integer productId, Integer brandId, String priceStartDate) throws ParseDateException, ResourceNotFoundException;

    Flux<Price> findPriceByParams(Integer productId, Integer brandId, String priceSearchDate) throws ParseDateException;
}
