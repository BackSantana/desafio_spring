package com.desafio_spring.demo.dto.post;

import com.desafio_spring.demo.model.user.User;

public class PostPromoCountDTO {
    private Integer id;
    private String userName;
    private int promoproducts_count;

    public PostPromoCountDTO(Integer id, String userName, int promoproducts_count) {
        this.id = id;
        this.userName = userName;
        this.promoproducts_count = promoproducts_count;
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public int getPromoproducts_count() {
        return promoproducts_count;
    }

    public static PostPromoCountDTO postPromoCountDTO(User user, int promoproducts_count){
        return new PostPromoCountDTO(user.getId(), user.getName(), promoproducts_count);
    }
}
