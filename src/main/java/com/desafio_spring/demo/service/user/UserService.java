package com.desafio_spring.demo.service.user;

import com.desafio_spring.demo.dto.user.FollowersCountDTO;
import com.desafio_spring.demo.dto.user.FollowersListDTO;
import com.desafio_spring.demo.exception.UserAlreadyFollowUser;
import com.desafio_spring.demo.model.user.*;
import com.desafio_spring.demo.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FollowSellerService followSellerService;

    public ResponseEntity followUser(User user, User userToFollow) {
        verifyExistUserFollow(user, userToFollow);
        followSellerService.followingRelationshipsFollow(user, userToFollow);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity unfollow(User user, User userToUnfollow){
        followSellerService.followingRelationshipsUnfollow(user, userToUnfollow);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<FollowersCountDTO> followersCount(User user){
        int followersCount = followSellerService.countFollowers(user.getFollowSellers());
        FollowersCountDTO followersDTO = new FollowersCountDTO();
        return  ResponseEntity.ok().body(followersDTO.userToFollowerCountDTO(user, followersCount));
     }

    public void verifyExistUserFollow(User userId, User userIdToFollow){
        Optional<FollowSeller> user = followSellerService.getFollowing(userId, userIdToFollow, "S");
        if(user.isPresent())
            throw new UserAlreadyFollowUser(String.format("You already follow the user %s", userIdToFollow));
    }

    public ResponseEntity<FollowersListDTO> getUserByTypeList(User user, TypeUser typeUser){
        List<FollowSeller> followersList = userRepository.getUserByType(user.getFollowSellers(), typeUser);
        FollowersListDTO followersListDTO = new FollowersListDTO();
        return ResponseEntity.ok().body(followersListDTO.userToListByClient(user, followersList));
    }

    public User getUser(Integer id){
        return userRepository.getUser(id);
    }
}
