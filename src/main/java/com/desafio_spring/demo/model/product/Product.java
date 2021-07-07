package com.desafio_spring.demo.model.product;

public class Product {
    private Long id;
    private String name;
    private String type;
    private String brand;
    private String color;
    private String notes;

    public Product(Long id, String name, String type, String brand, String color, String notes) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.brand = brand;
        this.color = color;
        this.notes = notes;
    }
}
