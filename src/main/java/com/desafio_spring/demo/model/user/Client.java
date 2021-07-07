package com.desafio_spring.demo.model.user;

import java.util.List;

public class Client extends User{

    public Client(Integer id, String name, TypeUser type, List<FollowingRelationships> followingRelationships) {
        super(id, name, TypeUser.CLIENT, followingRelationships);
    }
}
