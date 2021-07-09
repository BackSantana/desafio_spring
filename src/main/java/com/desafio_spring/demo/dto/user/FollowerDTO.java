package com.desafio_spring.demo.dto.user;

public class FollowerDTO {
    private Integer userId;
    private String nome;

    public FollowerDTO(Integer userId, String nome) {
        this.userId = userId;
        this.nome = nome;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getNome() {
        return nome;
    }
}
