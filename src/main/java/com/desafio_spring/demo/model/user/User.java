package com.desafio_spring.demo.model.user;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;
    private String name;
    private TypeUser type;
    private List<FollowingRelationships> followingRelationships;

    public User(Long id, String name, TypeUser type, List<FollowingRelationships> followingRelationships) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.followingRelationships = new ArrayList<>();
    }

    public void setType(TypeUser type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setFollowingRelationshipsFollow(FollowingRelationships userToFollow) {
        this.followingRelationships.add(userToFollow);
    }


}
