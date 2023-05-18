package com.demo.inditex.price.infraestructure.adapters.in;

import com.demo.inditex.price.infraestructure.Exceptions.ParseDateException;
import com.demo.inditex.price.infraestructure.dtos.ErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class ControllerAdvice {

    private Logger log = LoggerFactory.getLogger(ControllerAdvice.class);

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> illegalArgumentExceptionHandler(RuntimeException ex){
        ErrorDTO error = new ErrorDTO(HttpStatus.BAD_REQUEST,ex.getMessage(), OffsetDateTime.now());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ParseDateException.class)
    public ResponseEntity<ErrorDTO> parseDateExceptionHandler(RuntimeException ex){
        log.info("Error parsing dates -> {}", ex.toString());
        ErrorDTO error = new ErrorDTO(HttpStatus.BAD_REQUEST, ex.getMessage(), OffsetDateTime.now());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
