package com.freitas.lucas.carregistration.error.exceptions;

public class ObjectAlreadyExists extends RuntimeException {

    public ObjectAlreadyExists(String message) {
        super(message);
    }

    public ObjectAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }
}
