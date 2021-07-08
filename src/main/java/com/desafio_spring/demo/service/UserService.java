package com.desafio_spring.demo.service;

import com.desafio_spring.demo.dto.FollowersDTO;
import com.desafio_spring.demo.exception.UserAlreadyFollowUser;
import com.desafio_spring.demo.model.user.*;
import com.desafio_spring.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ResponseEntity followUser(Integer userId, Integer userIdToFollow) {
        User user = userRepository.getUser(userId);
        User userToFollow = userRepository.getUser(userIdToFollow);
        verifyExistUserFollow(user, userToFollow);

        userRepository.followingRelationshipsFollow(user, userToFollow);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity unfollow(Integer userId, Integer userIdToUnfollow){
        User user = userRepository.getUser(userId);
        User userToUnfollow = userRepository.getUser(userIdToUnfollow);
        userRepository.followingRelationshipsUnfollow(user, userToUnfollow);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<FollowersDTO> followers(Integer userId){
        User user = userRepository.getUser(userId);
        int followersCount = userRepository.countFollowers(user.getFollowingRelationships());
        FollowersDTO followersDTO = new FollowersDTO();
        return  ResponseEntity.ok().body(followersDTO.userToFollowerCountDTO(user, followersCount));
     }

    public void verifyExistUserFollow(User userId, User userIdToFollow){
        Optional<FollowingRelationships> user = userId.getFollowingRelationships()
                .stream()
                .filter(u -> u.getUser_id() == userIdToFollow.getId())
                .findFirst();

        if(user.isPresent())
            throw new UserAlreadyFollowUser(String.format("You already follow the user %s", userIdToFollow));
    }
}
