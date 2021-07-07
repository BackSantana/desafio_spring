package com.desafio_spring.demo.model.user;

import java.util.List;

public class Seller extends User{

    public Seller(Integer id, String name, TypeUser type, List<FollowingRelationships> followingRelationships) {
        super(id, name, TypeUser.SELLER, followingRelationships);
    }
}
