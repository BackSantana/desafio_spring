package com.desafio_spring.demo.model.user;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Integer id;
    private String name;
    private TypeUser type;
    private List<FollowSeller> followSellers;

    public User(Integer id, String name, TypeUser type, List<FollowSeller> followingRelationships) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.followSellers = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TypeUser getType() {
        return type;
    }

    public List<FollowSeller> getFollowSellers() {
        return followSellers;
    }

    public void add(FollowSeller followingRelationships) {
        this.followSellers.add(followingRelationships);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
