package com.demo.inditex.price.infrastructure.Exceptions;

import lombok.Getter;

@Getter
public class ParseDateException extends Exception{

    public ParseDateException(String errorMessage) {
        super(errorMessage);
    }
}
