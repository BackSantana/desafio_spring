package com.desafio_spring.demo.exception;

public class UserDoesNotExistingException extends UserException{
    public UserDoesNotExistingException(String message) {
        super(message);
    }
}
