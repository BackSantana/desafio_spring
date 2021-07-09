package com.desafio_spring.demo.controller;

import com.desafio_spring.demo.model.user.TypeUser;
import com.desafio_spring.demo.model.user.User;
import com.desafio_spring.demo.service.user.UserService;
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
        userService.addUserTemporary();
        User user = userService.getUser(userId);
        User userToFollow = userService.getUser(userIdToFollow);
        return this.userService.followUser(user, userToFollow);
    }

    //US 0007
    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity unfollowUser(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow){
        User user = userService.getUser(userId);
        User userToUnfollow = userService.getUser(userIdToUnfollow);
        return this.userService.unfollow(user, userToUnfollow);
    }

    //US 0002
    @GetMapping("/{userId}/followers/count")
    public ResponseEntity followersCount(@PathVariable Integer userId){
        User user = userService.getUser(userId);
        return this.userService.followersCount(user);
    }

    //US 0003
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity followersList(@PathVariable Integer userId){
        User user = userService.getUser(userId);
        return this.userService.getUserByTypeList(user, TypeUser.CLIENT);
    }

    //US 0004
    @GetMapping("{userId}/followed/list")
    public ResponseEntity followedList(@PathVariable Integer userId){
        User user = userService.getUser(userId);
        return this.userService.getUserByTypeList(user, TypeUser.SELLER);
    }
}
