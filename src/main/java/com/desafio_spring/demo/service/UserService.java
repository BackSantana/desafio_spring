package com.desafio_spring.demo.service;

import com.desafio_spring.demo.dto.FollowersCountDTO;
import com.desafio_spring.demo.dto.FollowersListDTO;
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

    public ResponseEntity followUser(User user, User userToFollow) {
        verifyExistUserFollow(user, userToFollow);
        userRepository.followingRelationshipsFollow(user, userToFollow);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity unfollow(User user, User userToUnfollow){
        userRepository.followingRelationshipsUnfollow(user, userToUnfollow);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<FollowersCountDTO> followersCount(User user){
        int followersCount = userRepository.countFollowers(user.getFollowingRelationships());
        FollowersCountDTO followersDTO = new FollowersCountDTO();
        return  ResponseEntity.ok().body(followersDTO.userToFollowerCountDTO(user, followersCount));
     }

    public void verifyExistUserFollow(User userId, User userIdToFollow){
        Optional<FollowingRelationships> user = userRepository.getFollwing(userId, userIdToFollow, "S");
        if(user.isPresent())
            throw new UserAlreadyFollowUser(String.format("You already follow the user %s", userIdToFollow));
    }

    public ResponseEntity<FollowersListDTO> getUserByTypeList(User user, TypeUser typeUser){
        List<FollowingRelationships> followersList = userRepository.getUserByType(user.getFollowingRelationships(), typeUser);
        FollowersListDTO followersListDTO = new FollowersListDTO();
        return ResponseEntity.ok().body(followersListDTO.userToListByClient(user, followersList));
    }

    public User getUser(Integer id){
        return userRepository.getUser(id);
    }
}
