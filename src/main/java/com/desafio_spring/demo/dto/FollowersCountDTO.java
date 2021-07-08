package com.desafio_spring.demo.dto;

import com.desafio_spring.demo.model.user.User;

public class FollowersCountDTO {

    private Integer id;
    private String userName;
    private int fallowers_count;

    public FollowersCountDTO(Integer id, String userName, int fallowers_count) {
        this.id = id;
        this.userName = userName;
        this.fallowers_count = fallowers_count;
    }

    public FollowersCountDTO(){

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

    public FollowersCountDTO userToFollowerCountDTO(User user, int followersCount){
        return new FollowersCountDTO(user.getId(),user.getName(), followersCount);
    }

}
