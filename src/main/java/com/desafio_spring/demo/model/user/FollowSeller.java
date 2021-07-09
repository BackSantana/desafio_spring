package com.desafio_spring.demo.model.user;

public class FollowSeller {
    private Integer user_id;
    private String nome;
    private TypeFollowingRelationships typeRelationships;

    public FollowSeller(Integer user_id, String nome, TypeFollowingRelationships typeRelationships) {
        this.user_id = user_id;
        this.nome = nome;
        this.typeRelationships = typeRelationships;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public String getNome() {
        return nome;
    }

    public TypeFollowingRelationships getTypeRelationships() {
        return typeRelationships;
    }
}
