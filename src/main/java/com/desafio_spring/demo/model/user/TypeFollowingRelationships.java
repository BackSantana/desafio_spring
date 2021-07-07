package com.desafio_spring.demo.model.user;

public enum TypeFollowingRelationships {
    FOLLOWER("FOLLOWER"), FOLLOWED("FOLLOWED");

    private String name;

    TypeFollowingRelationships(String name){
        this.name = name;
    }
}
