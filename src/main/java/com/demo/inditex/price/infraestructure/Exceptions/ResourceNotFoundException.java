package com.demo.inditex.price.infraestructure.Exceptions;

public class ResourceNotFoundException extends RuntimeException{

    private String message;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
