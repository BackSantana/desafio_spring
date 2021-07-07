package com.desafio_spring.demo.model.user;

public class FollowingRelationships {
    private Long user_id;
    private TypeFollowingRelationships typeRelationships;

    public FollowingRelationships(Long user_id, TypeFollowingRelationships typeRelationships) {
        this.user_id = user_id;
        this.typeRelationships = typeRelationships;
    }
}
