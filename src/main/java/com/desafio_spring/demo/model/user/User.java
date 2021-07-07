package com.desafio_spring.demo.model.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private Integer id;
    private String name;
    private TypeUser type;
    private List<FollowingRelationships> followingRelationships;

    public User(Integer id, String name, TypeUser type, List<FollowingRelationships> followingRelationships) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.followingRelationships = new ArrayList<>();
    }

    public void setType(TypeUser type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setFollowingRelationshipsFollow(FollowingRelationships userToFollow) {
        this.followingRelationships.add(userToFollow);
    }

    public List<FollowingRelationships> getFollowingRelationships() {
        return followingRelationships;
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
