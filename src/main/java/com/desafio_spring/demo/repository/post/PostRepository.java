package com.desafio_spring.demo.repository.post;

import com.desafio_spring.demo.model.post.Post;
import com.desafio_spring.demo.model.user.FollowSeller;
import com.desafio_spring.demo.model.user.Seller;
import com.desafio_spring.demo.model.user.User;
import com.desafio_spring.demo.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PostRepository {

    @Autowired
    UserRepository userRepository;

    public List<Post> addPosts(List<Seller> followSellers){
        List<Post> posts = new ArrayList<>();
        followSellers.forEach(f -> f.getPosts().forEach( post -> posts.add(post)));
        return posts;
    }
     public List<Seller> getUserByType(List<FollowSeller> followSellers){
        List<Seller> sellers = new ArrayList<>();
        followSellers.stream().forEach(f -> sellers.add((Seller) userRepository.getUser(f.getUser_id())));
        return sellers;
    }

    public List<Post> getPostsHasPromo(Seller seller){
        List<Post> posts = seller.getPosts();
        return posts.stream()
                .filter(post -> post.getHasPromo() == true)
                .collect(Collectors.toList());
    }
}
