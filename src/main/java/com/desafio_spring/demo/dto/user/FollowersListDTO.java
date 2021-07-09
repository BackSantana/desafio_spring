package com.desafio_spring.demo.dto.user;

import com.desafio_spring.demo.model.user.FollowSeller;
import com.desafio_spring.demo.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class FollowersListDTO {

    private Integer userId;
    private String name;
    private List<FollowerDTO> followers;

    public FollowersListDTO(Integer userId, String name, List<FollowerDTO> Followers) {
        this.userId = userId;
        this.name = name;
        this.followers = Followers;
    }

    public FollowersListDTO(){ }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public List<FollowerDTO> getFollowers() {
        return followers;
    }

    public FollowersListDTO userToListByClient(User user, List<FollowSeller> followers){
        List<FollowerDTO> followersAux = followingRelationshipsToFollowers(followers);
        return new FollowersListDTO(user.getId(), user.getName(), followersAux);
    }

    public List<FollowerDTO> followingRelationshipsToFollowers(List<FollowSeller> followingRelationships){
        List<FollowerDTO> followers = new ArrayList<>();
        followingRelationships.forEach( f -> followers.add(new FollowerDTO(f.getUser_id(), f.getNome())));
        return followers;
    }
}
