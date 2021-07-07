package com.desafio_spring.demo.model.user;

public enum TypeUser {
    SELLER("SELLER"),
    CLIENT("CLIENT");

    private String name;

    TypeUser(String name){
        this.name = name;
    }
}
