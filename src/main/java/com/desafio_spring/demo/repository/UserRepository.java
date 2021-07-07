package com.desafio_spring.demo.repository;

import com.desafio_spring.demo.Utils.GenerateID;
import com.desafio_spring.demo.exception.UserDoesNotExistingException;
import com.desafio_spring.demo.model.user.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    List<User> users = new ArrayList<User>();
    List<FollowingRelationships> usersFollowing;
    User userSeller1 = new Seller(GenerateID.getLastId(), "Carlos Santana", TypeUser.SELLER, usersFollowing);
    User userSeller2 = new Seller(GenerateID.getLastId(), "Carlos Sergio", TypeUser.SELLER, usersFollowing);
    User userClient1 = new Client(GenerateID.getLastId(), "Carlos Santos", TypeUser.CLIENT, usersFollowing);
    User userClient2 = new Client(GenerateID.getLastId(), "Santos Santana", TypeUser.CLIENT, usersFollowing);

    public User getUser(Integer id){
        addUserTemporary();
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(()-> new UserDoesNotExistingException(String.format("User [%s] not found", id)));
    }

    public void followingRelationshipsFollow(User user, User userToFollow){
        user.setFollowingRelationshipsFollow(new FollowingRelationships(userToFollow.getId(), TypeFollowingRelationships.FOLLOWED));
        userToFollow.setFollowingRelationshipsFollow(new FollowingRelationships(user.getId(), TypeFollowingRelationships.FOLLOWER));
    }

    public void followingRelationshipsUnfollow(User user, User userToUnfollow){
        FollowingRelationships followingRelationships1 = getFollwing(user, userToUnfollow, "S");
        FollowingRelationships followingRelationships2 = getFollwing(user, userToUnfollow, "N");

        user.getFollowingRelationships().remove(followingRelationships1);
        userToUnfollow.getFollowingRelationships().remove(followingRelationships2);
    }

    public FollowingRelationships getFollwing(User user, User userToUnfollow, String order){
        if(order.equals("S"))
            return user.getFollowingRelationships()
                    .stream()
                    .filter(u -> u.getUser_id() == userToUnfollow.getId())
                    .findFirst()
                    .get();
        else
            return userToUnfollow.getFollowingRelationships()
                    .stream()
                    .filter(u -> u.getUser_id() == user.getId())
                    .findFirst()
                    .get();
    }

    // Pretendo refatorar isso aqui, não está legal
    public void addUserTemporary(){
        users.add(userClient1);
        users.add(userClient2);
        users.add(userSeller1);
        users.add(userSeller2);
    }
}
