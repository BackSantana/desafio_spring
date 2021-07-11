package com.desafio_spring.demo.model.post;

import com.desafio_spring.demo.model.product.Product;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Post {
    private Integer id;
    private Integer user_id;
    private LocalDate date;
    private String category;
    private BigDecimal price;
    private Boolean hasPromo;
    private BigDecimal discount;
    private Product product;

    public Post(Integer id, Integer user_id, LocalDate date, String category, BigDecimal price, Product product) {
        this.id = id;
        this.user_id = user_id;
        this.date = date;
        this.category = category;
        this.price = price;
        this.product = product;
    }

    public Post(Integer id, Integer user_id, LocalDate date, String category, BigDecimal price, Boolean hasPromo, BigDecimal discount, Product product) {
        this.id = id;
        this.user_id = user_id;
        this.date = date;
        this.category = category;
        this.price = price;
        this.hasPromo = hasPromo;
        this.discount = discount;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Boolean getHasPromo() {
        return hasPromo;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getUser_id() {
        return user_id;
    }
}
