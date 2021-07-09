package com.desafio_spring.demo.dto.post;

import com.desafio_spring.demo.Utils.GenerateID;
import com.desafio_spring.demo.model.post.Post;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PostRequestDTO {

    private Integer user_id;
    private LocalDate date;
    private String category;
    private BigDecimal price;
    private ProductDTO product;

    public PostRequestDTO(Integer user_id, LocalDate date, String category, BigDecimal price, ProductDTO product) {
        this.user_id = user_id;
        this.date = date;
        this.category = category;
        this.price = price;
        this.product = product;
    }

    public PostRequestDTO(){

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public static Post DtoToPost(PostRequestDTO postRequest){
        return new Post(
                GenerateID.getLastId(),
                postRequest.getUser_id(),
                postRequest.getDate(),
                postRequest.getCategory(),
                postRequest.getPrice(),
                ProductDTO.productDTOToProducto(postRequest.getProduct()));
    }
}
