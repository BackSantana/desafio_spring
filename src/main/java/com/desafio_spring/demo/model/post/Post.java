package com.desafio_spring.demo.model.post;

import com.desafio_spring.demo.model.product.Product;
import com.desafio_spring.demo.model.user.Seller;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Post {
    private Integer id;
    private LocalDate date;
    private Seller seller;
    private String category;
    private BigDecimal price;
    private Boolean hasPromo;
    private BigDecimal discount;
    private Product product;

    public Post(Integer id, LocalDate date, Seller seller, String category, BigDecimal price, Product product) {
        this.id = id;
        this.date = date;
        this.seller = seller;
        this.category = category;
        this.price = price;
        this.product = product;
    }

    public Post(Integer id, LocalDate date, Seller seller, String category, BigDecimal price, Boolean hasPromo, BigDecimal discount, Product product) {
        this.id = id;
        this.date = date;
        this.seller = seller;
        this.category = category;
        this.price = price;
        this.hasPromo = hasPromo;
        this.discount = discount;
        this.product = product;
    }
}
