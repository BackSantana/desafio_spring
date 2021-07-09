package com.desafio_spring.demo.dto.post;

import com.desafio_spring.demo.Utils.GenerateID;
import com.desafio_spring.demo.model.product.Product;

public class ProductDTO {
    private String name;
    private String type;
    private String brand;
    private String color;
    private String notes;

    public ProductDTO(String name, String type, String brand, String color, String notes) {
        this.name = name;
        this.type = type;
        this.brand = brand;
        this.color = color;
        this.notes = notes;
    }
    public ProductDTO(){}

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public String getNotes() {
        return notes;
    }

    public static Product productDTOToProducto(ProductDTO productDTO){
        return new Product(
                GenerateID.getLastId(),
                productDTO.getName(),
                productDTO.getType(),
                productDTO.getBrand(),
                productDTO.getColor(),
                productDTO.getNotes());
    }

    public static ProductDTO productToDTO(Product product){
        return new ProductDTO(
                product.getName(),
                product.getType(),
                product.getBrand(),
                product.getColor(),
                product.getNotes());
    }
}
