package com.desafio_spring.demo.model.user;

import com.desafio_spring.demo.model.post.Post;

import java.util.ArrayList;
import java.util.List;

public class Seller extends User{
    private List<Post> posts = new ArrayList<>();

    public Seller(Integer id, String name, List<FollowSeller> followingRelationships) {
        super(id, name, TypeUser.SELLER, followingRelationships);
    }

    public List<Post> getPosts() {
        return posts;
    }
}
