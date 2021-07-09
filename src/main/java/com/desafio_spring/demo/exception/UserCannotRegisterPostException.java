package com.desafio_spring.demo.exception;

public class UserCannotRegisterPostException extends  UserException{
    public UserCannotRegisterPostException(String message) {
        super(message);
    }
}
