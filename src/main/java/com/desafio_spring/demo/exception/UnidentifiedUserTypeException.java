package com.desafio_spring.demo.exception;

public class UnidentifiedUserTypeException extends UserException{
    public UnidentifiedUserTypeException(String message) {
        super(message);
    }
}
