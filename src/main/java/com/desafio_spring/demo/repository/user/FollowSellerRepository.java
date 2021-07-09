package com.desafio_spring.demo.repository.user;

import com.desafio_spring.demo.model.user.FollowSeller;
import com.desafio_spring.demo.model.user.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FollowSellerRepository {
    /*
    Esta função está percorrendo a list do FollowingRelationships que está dentro do objeto User e vai retorna o
    objeto correto para a exclusão da lista.
     */
    public Optional<FollowSeller> getFollowing(User user, User userToUnfollow, String order){
        if(order.equals("S"))
            return user.getFollowSellers()
                    .stream()
                    .filter(u -> u.getUser_id() == userToUnfollow.getId())
                    .findFirst();
        else
            return userToUnfollow.getFollowSellers()
                    .stream()
                    .filter(u -> u.getUser_id() == user.getId())
                    .findFirst();
    }
}
