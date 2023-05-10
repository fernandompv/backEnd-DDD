package com.demo.inditex.price.infraestructure.Exceptions;

import lombok.Getter;

@Getter
public class ParseDateException extends Exception{

    public ParseDateException(String errorMessage) {
        super(errorMessage);
    }
}
