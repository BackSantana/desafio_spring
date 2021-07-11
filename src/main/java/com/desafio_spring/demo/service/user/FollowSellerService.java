package com.desafio_spring.demo.service.user;

import com.desafio_spring.demo.exception.UserDoesNotExistingException;
import com.desafio_spring.demo.model.user.FollowSeller;
import com.desafio_spring.demo.model.user.TypeFollowingRelationships;
import com.desafio_spring.demo.model.user.User;
import com.desafio_spring.demo.repository.user.FollowSellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FollowSellerService {

    @Autowired
    FollowSellerRepository followSellerRepository;

    public Optional<FollowSeller> getFollowing(User user, User userToUnfollow, String order){
        return followSellerRepository.getFollowing(user, userToUnfollow, order);
    }

    /*
    Faz a contagem de followers
     */
    public int countFollowers(List<FollowSeller> followingRelationships) {
        Long countFollowers = followingRelationships
                .stream()
                .filter(f -> f.getTypeRelationships().equals(TypeFollowingRelationships.FOLLOWER))
                .count();
        return countFollowers.intValue();
    }

    public void followingRelationshipsUnfollow(User user, User userToUnfollow){
        FollowSeller followSeller = getFollowing(user, userToUnfollow, "S")
                .orElseThrow(()-> new UserDoesNotExistingException(String.format("User [%s] not found", userToUnfollow)));

        FollowSeller followSellerUnfollow = getFollowing(user, userToUnfollow, "N")
                .orElseThrow(()-> new UserDoesNotExistingException(String.format("User [%s] not found", user)));;

        user.getFollowSellers().remove(followSeller);
        userToUnfollow.getFollowSellers().remove(followSellerUnfollow);
    }

    /*
  Função que faz o follow. No FollowingRelationships é adicionado o tipo do relacionamento.
   */
    public void followingRelationshipsFollow(User user, User userToFollow){
        user.add(new FollowSeller( userToFollow.getId(), userToFollow.getName(), TypeFollowingRelationships.FOLLOWED));
        userToFollow.add(new FollowSeller( user.getId(), user.getName(), TypeFollowingRelationships.FOLLOWER));
    }
}
