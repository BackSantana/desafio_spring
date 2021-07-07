package com.desafio_spring.demo.service;

import com.desafio_spring.demo.model.user.*;
import com.desafio_spring.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ResponseEntity followUser(Long userId, Long userIdToFollow) {
        User user = userRepository.getUser(userIdToFollow);
        User user1 = userRepository.getUser(userId);

        user.setFollowingRelationshipsFollow(new FollowingRelationships(userIdToFollow, TypeFollowingRelationships.FOLLOWED));
        user1.setFollowingRelationshipsFollow(new FollowingRelationships(userId, TypeFollowingRelationships.FOLLOWER));

        return ResponseEntity.ok().build();
    }
}
