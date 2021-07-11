package com.desafio_spring.demo.dto.post;

import com.desafio_spring.demo.Utils.GenerateID;
import com.desafio_spring.demo.model.post.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PostRequestDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer user_id;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate date;
    private ProductDTO product;
    private String category;
    private BigDecimal price;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean hasPromo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal discount;

    public PostRequestDTO(Integer user_id, LocalDate date, String category, BigDecimal price, ProductDTO product) {
        this.user_id = user_id;
        this.date = date;
        this.category = category;
        this.price = price;
        this.product = product;
    }

    public PostRequestDTO(Integer user_id, LocalDate date, ProductDTO product, String category, BigDecimal price, Boolean hasPromo, BigDecimal discount) {
        this.user_id = user_id;
        this.date = date;
        this.product = product;
        this.category = category;
        this.price = price;
        this.hasPromo = hasPromo;
        this.discount = discount;
    }

    public PostRequestDTO(){ }

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getHasPromo() {
        return hasPromo;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public static Post DtoToPost(PostRequestDTO postRequest){
        return new Post(
                GenerateID.getLastId(),
                postRequest.getUser_id(),
                postRequest.getDate(),
                postRequest.getCategory(),
                postRequest.getPrice(),
                false,
                BigDecimal.valueOf(0),
                ProductDTO.productDTOToProducto(postRequest.getProduct()));
    }

    public static Post DtoToPostHasPromo(PostRequestDTO postRequest){
        return new Post(
                GenerateID.getLastId(),
                postRequest.getUser_id(),
                postRequest.getDate(),
                postRequest.getCategory(),
                postRequest.getPrice(),
                postRequest.getHasPromo(),
                postRequest.getDiscount(),
                ProductDTO.productDTOToProducto(postRequest.getProduct()));
    }
}
