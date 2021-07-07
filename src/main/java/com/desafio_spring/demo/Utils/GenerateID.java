package com.desafio_spring.demo.Utils;

public class GenerateID {
    public static Long id = 1L;

    public static Long getLastId() {
        return id++;
    }
}
