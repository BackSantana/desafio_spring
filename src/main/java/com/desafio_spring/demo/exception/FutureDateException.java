package com.desafio_spring.demo.exception;

public class FutureDateException extends RuntimeException{
    public FutureDateException(String message) {
        super(message);
    }
}
