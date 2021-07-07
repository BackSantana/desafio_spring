package com.desafio_spring.demo.repository;

import com.desafio_spring.demo.Utils.GenerateID;
import com.desafio_spring.demo.exception.UserDoesNotExistingException;
import com.desafio_spring.demo.model.user.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    List<User> users = new ArrayList<User>();
    List<FollowingRelationships> usersFollowing;
    User userSeller1 = new Seller(GenerateID.getLastId(), "Carlos Santana", TypeUser.SELLER, usersFollowing);
    User userSeller2 = new Seller(GenerateID.getLastId(), "Carlos Sergio", TypeUser.SELLER, usersFollowing);
    User userClient1 = new Client(GenerateID.getLastId(), "Carlos Santos", TypeUser.CLIENT, usersFollowing);
    User userClient2 = new Client(GenerateID.getLastId(), "Santos Santana", TypeUser.CLIENT, usersFollowing);

    public User getUser(Long id){
        addUserTemporary();
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(()-> new UserDoesNotExistingException(String.format("User [%s] not found", id)));
    }

    // Pretendo refatorar isso aqui, não está legal
    public void addUserTemporary(){
        users.add(userClient1);
        users.add(userClient2);
        users.add(userSeller1);
        users.add(userSeller2);
    }
}
