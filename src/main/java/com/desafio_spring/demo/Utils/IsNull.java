package com.desafio_spring.demo.Utils;

import com.desafio_spring.demo.model.user.User;

public class IsNull {

    public User isNullUser(User user){
        if(user != null)
            return user;
        return null;
    }
}
