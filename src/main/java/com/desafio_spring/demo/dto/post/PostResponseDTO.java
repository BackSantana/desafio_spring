package com.desafio_spring.demo.dto.post;

import com.desafio_spring.demo.model.post.Post;
import java.math.BigDecimal;
import java.time.LocalDate;

public class PostResponseDTO {
    private Integer user_id;
    private Integer id_post;
    private LocalDate date;
    private ProductDTO product;
    private String category;
    private BigDecimal price;

    public PostResponseDTO(Integer user_id, Integer id_post, LocalDate date, ProductDTO product, String category, BigDecimal price) {
        this.user_id = user_id;
        this.id_post = id_post;
        this.date = date;
        this.category = category;
        this.price = price;
        this.product = product;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getId_post() {
        return id_post;
    }

    public void setId_post(Integer id_post) {
        this.id_post = id_post;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
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

    public static PostResponseDTO postResponse(Post post, ProductDTO productDTO){
        return new PostResponseDTO(
                post.getUser_id(),
                post.getId(),
                post.getDate(),
                productDTO,
                post.getCategory(),
                post.getPrice());
    }
}
