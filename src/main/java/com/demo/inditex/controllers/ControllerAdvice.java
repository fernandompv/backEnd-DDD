package com.demo.inditex.controllers;

import com.demo.inditex.Exceptions.ParseDateException;
import com.demo.inditex.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorDTO> runtimeExceptionHandler(RuntimeException ex){
        ErrorDTO error = new ErrorDTO(HttpStatus.BAD_REQUEST,ex.toString(), OffsetDateTime.now());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ParseDateException.class)
    public ResponseEntity<ErrorDTO> parseDateExceptionHandler(RuntimeException ex){
        ErrorDTO error = new ErrorDTO(HttpStatus.BAD_REQUEST,ex.toString(), OffsetDateTime.now());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
