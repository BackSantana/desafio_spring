package com.desafio_spring.demo.exception;

public class SellerCannotFollowUserException extends UserException {
    public SellerCannotFollowUserException(String message) {
        super(message);
    }
}
