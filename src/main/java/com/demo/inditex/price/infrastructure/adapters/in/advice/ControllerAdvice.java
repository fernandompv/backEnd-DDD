package com.demo.inditex.price.infrastructure.adapters.in.advice;

import com.demo.inditex.Codegen.dto.ErrorResponseDTO;
import com.demo.inditex.price.infrastructure.Exceptions.ParseDateException;
import com.demo.inditex.util.ErrorDictionary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ServerWebInputException;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class ControllerAdvice {

    private Logger log = LoggerFactory.getLogger(ControllerAdvice.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception ex) {
        log.error("Unexpected error, if it`s a known error it´s necessary to create a specific exception -> {}",ex.toString());
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ErrorDictionary.UNKNOW_ERROR_MESSAGE,OffsetDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServerWebInputException.class)
    public ResponseEntity<ErrorResponseDTO> handleClientException(ServerWebInputException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value() ,
                ErrorDictionary.RESOURCE_NOT_FOUND_MESSAGE,OffsetDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ParseDateException.class)
    public ResponseEntity<ErrorResponseDTO> parseDateExceptionHandler(ParseDateException ex){
        log.info("Error parsing dates -> {}", ex.toString());
        ErrorResponseDTO error = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), OffsetDateTime.now());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
