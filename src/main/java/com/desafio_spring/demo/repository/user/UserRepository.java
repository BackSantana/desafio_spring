package com.desafio_spring.demo.repository.user;

import com.desafio_spring.demo.Utils.GenerateID;
import com.desafio_spring.demo.exception.UserDoesNotExistingException;
import com.desafio_spring.demo.model.user.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    static List<User> users = new ArrayList<User>();
    List<FollowSeller> usersFollowing ;
    User userSeller1 = new Seller(GenerateID.getLastId(), "Carlos Santana", TypeUser.SELLER, usersFollowing);
    User userSeller2 = new Seller(GenerateID.getLastId(), "Carlos Sergio", TypeUser.SELLER, usersFollowing);
    User userClient1 = new Client(GenerateID.getLastId(), "Carlos Santos", TypeUser.CLIENT, usersFollowing);
    User userClient2 = new Client(GenerateID.getLastId(), "Santos Santana", TypeUser.CLIENT, usersFollowing);

    /*
    getUser recebe um id e retorna o objeto User cadastrado com esse is. Caso não existe é lançado um throw.
     */
    public User getUser(Integer id){
        addUserTemporary();
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(()-> new UserDoesNotExistingException(String.format("User [%s] not found", id)));
    }

//    /*
//    getUser recebe um id e retorna o objeto User cadastrado com esse is. Caso não existe é lançado um throw.
//     */
//    public Seler getSeller(Integer id){
//        return users.stream()
//                .filter(u -> u.getId() == id)
//                .findFirst()
//                .orElseThrow(()-> new UserDoesNotExistingException(String.format("User [%s] not found", id)));
//    }

    /*
     Recebe um lista de FollowSellers e retorna um lista de FollowSellers de acordo com o tipo.
     */
    public List<FollowSeller> getUserByType(List<FollowSeller> followSellers, TypeUser typeUser){
        return followSellers
                .stream()
                .filter(u -> getUser(u.getUser_id()).getType().equals(typeUser))
                .collect(Collectors.toList());
     }

    // Pretendo refatorar isso aqui, não está legal
    public void addUserTemporary(){
        users.add(userClient1);
        users.add(userClient2);
        users.add(userSeller1);
        users.add(userSeller2);
    }
}
