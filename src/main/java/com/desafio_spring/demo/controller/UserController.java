package com.desafio_spring.demo.controller;

import com.desafio_spring.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
     }

    //US 0001
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity followUser(@PathVariable Integer userId, @PathVariable Integer userIdToFollow){
        return this.userService.followUser(userId, userIdToFollow);
    }

    //US 0007
    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity unfollowUser(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow){
        return this.userService.unfollow(userId, userIdToUnfollow);
    }

    //US 0002
    @GetMapping("/{userId}/followers/count")
    public ResponseEntity followers(@PathVariable Integer userId){
        return this.userService.followers(userId);
    }
}
