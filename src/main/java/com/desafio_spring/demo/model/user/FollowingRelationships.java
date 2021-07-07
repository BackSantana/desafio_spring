package com.desafio_spring.demo.model.user;

public class FollowingRelationships {
    private Integer user_id;
    private TypeFollowingRelationships typeRelationships;

    public FollowingRelationships(Integer user_id, TypeFollowingRelationships typeRelationships) {
        this.user_id = user_id;
        this.typeRelationships = typeRelationships;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public TypeFollowingRelationships getTypeRelationships() {
        return typeRelationships;
    }
}
