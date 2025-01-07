package com.agilstore.agilstore.genericExceptions.dtos;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
