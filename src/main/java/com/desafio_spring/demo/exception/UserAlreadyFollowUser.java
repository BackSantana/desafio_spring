package com.desafio_spring.demo.exception;

import com.desafio_spring.demo.exception.UserException;

public class UserAlreadyFollowUser extends UserException {
    public UserAlreadyFollowUser(String message) {
        super(message);
    }
}
