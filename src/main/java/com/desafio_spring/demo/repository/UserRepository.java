package com.desafio_spring.demo.repository;

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
    List<FollowingRelationships> usersFollowing ;
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

    /*
     Recebe um followingRelationships e retorna um lista de followingRelationships de acordo com o tipo.
     */
    public List<FollowingRelationships> getUserByType(List<FollowingRelationships> followingRelationships, TypeUser typeUser){
        return followingRelationships
                .stream()
                .filter(u -> getUser(u.getUser_id()).getType().equals(typeUser))
                .collect(Collectors.toList());
     }

     /*
     Função que faz o follow. No FollowingRelationships é adicionado o tipo do relacionamento.
      */
    public void followingRelationshipsFollow(User user, User userToFollow){
        user.setFollowingRelationshipsFollow(
                new FollowingRelationships(
                        userToFollow.getId(),
                        user.getName(),
                        TypeFollowingRelationships.FOLLOWED));
        userToFollow.setFollowingRelationshipsFollow(
                new FollowingRelationships(
                        user.getId(),
                        user.getName(),
                        TypeFollowingRelationships.FOLLOWER));
    }

    public void followingRelationshipsUnfollow(User user, User userToUnfollow){
        FollowingRelationships followingRelationships1 = getFollwing(user, userToUnfollow, "S")
                .orElseThrow(()-> new UserDoesNotExistingException(String.format("User [%s] not found", userToUnfollow)));

        FollowingRelationships followingRelationships2 = getFollwing(user, userToUnfollow, "N")
                .orElseThrow(()-> new UserDoesNotExistingException(String.format("User [%s] not found", user)));;

        user.getFollowingRelationships().remove(followingRelationships1);
        userToUnfollow.getFollowingRelationships().remove(followingRelationships2);
    }

    /*
    Esta função está percorrendo a list do FollowingRelationships que está dentro do objeto User e vai retorna o
    objeto correto para a exclusão da lista.
     */
    public Optional<FollowingRelationships> getFollwing(User user, User userToUnfollow, String order){
        if(order.equals("S"))
            return user.getFollowingRelationships()
                    .stream()
                    .filter(u -> u.getUser_id() == userToUnfollow.getId())
                    .findFirst();
        else
            return userToUnfollow.getFollowingRelationships()
                    .stream()
                    .filter(u -> u.getUser_id() == user.getId())
                    .findFirst();
    }

    /*
    Faz a contagem de followers
     */
    public int countFollowers(List<FollowingRelationships> followingRelationships){
        Long countFollowers = followingRelationships
                .stream()
                .filter(f -> f.getTypeRelationships().equals(TypeFollowingRelationships.FOLLOWER))
                .count();
        return countFollowers.intValue();
    }

    // Pretendo refatorar isso aqui, não está legal
    public void addUserTemporary(){
        users.add(userClient1);
        users.add(userClient2);
        users.add(userSeller1);
        users.add(userSeller2);
    }
}
