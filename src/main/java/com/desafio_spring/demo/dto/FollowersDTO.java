package com.desafio_spring.demo.dto;

import com.desafio_spring.demo.model.user.User;

public class FollowersDTO {

    private Integer id;
    private String userName;
    private int fallowers_count;

    public FollowersDTO(Integer id, String userName, int fallowers_count) {
        this.id = id;
        this.userName = userName;
        this.fallowers_count = fallowers_count;
    }

    public FollowersDTO(){

    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public int getFallowers_count() {
        return fallowers_count;
    }

    public FollowersDTO userToFollowerCountDTO(User user, int followersCount){
        return new FollowersDTO(user.getId(),user.getName(), followersCount);
    }

}
