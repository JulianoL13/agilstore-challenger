package com.agilstore.agilstore.genericExceptions.dtos;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
