package com.desafio_spring.demo.exception.handler;

import com.desafio_spring.demo.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(value =  {
            UserDoesNotExistingException.class,
            UserAlreadyFollowUser.class,
            UserCannotRegisterPostException.class})
    public ResponseEntity exception(UserException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(value =  {FutureDateException.class,})
    public ResponseEntity exception(FutureDateException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
