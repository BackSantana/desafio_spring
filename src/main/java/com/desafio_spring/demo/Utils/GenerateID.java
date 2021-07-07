package com.desafio_spring.demo.Utils;

public class GenerateID {
    public static Integer id = 0;

    public static Integer getLastId() {
        return id++;
    }
}
