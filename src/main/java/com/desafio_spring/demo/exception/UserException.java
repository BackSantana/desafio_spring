package com.desafio_spring.demo.exception;

public class UserException extends RuntimeException{
    public UserException(String message) {
        super(message);
    }
}
