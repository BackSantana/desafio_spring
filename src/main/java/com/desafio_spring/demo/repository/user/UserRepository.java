package com.desafio_spring.demo.repository.user;

import com.desafio_spring.demo.exception.UserDoesNotExistingException;
import com.desafio_spring.demo.model.user.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    static List<User> users = new ArrayList<User>();

    /*
    getUser recebe um id e retorna o objeto User cadastrado com esse is. Caso não existe é lançado um throw.
     */
    public User getUser(Integer id){
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(()-> new UserDoesNotExistingException(String.format("User [%s] not found", id)));
    }

    /*
     Recebe um lista de FollowSellers e retorna um lista de FollowSellers de acordo com o tipo.
     */
    public List<FollowSeller> getUserByType(List<FollowSeller> followSellers, TypeUser typeUser){
        return followSellers
                .stream()
                .filter(u -> getUser(u.getUser_id()).getType().equals(typeUser))
                .collect(Collectors.toList());
     }

     public User addUser(User user){
        users.add(user);
        return getUser(user.getId());
    }
}
