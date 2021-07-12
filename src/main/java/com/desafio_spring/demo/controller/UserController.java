package com.desafio_spring.demo.controller;

import com.desafio_spring.demo.dto.user.UserRequestDTO;
import com.desafio_spring.demo.dto.user.UserResponseDTO;
import com.desafio_spring.demo.exception.UnidentifiedUserTypeException;
import com.desafio_spring.demo.model.user.TypeUser;
import com.desafio_spring.demo.model.user.User;
import com.desafio_spring.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
     }

    //US 0013
    @PostMapping("/registerUser")
    public ResponseEntity addUser(@RequestBody UserRequestDTO userDTO){
        User user;
        if(userDTO.getType().toUpperCase().equals(String.valueOf(TypeUser.SELLER))){
             user = UserRequestDTO.userDtoToSeller(userDTO);
        }else if(userDTO.getType().toUpperCase().equals(String.valueOf(TypeUser.CLIENT))){
             user = UserRequestDTO.userDtoToClient(userDTO);
        }else
            throw new UnidentifiedUserTypeException(String.format("Unidentified user type [ %s ]", userDTO.getType()));

        return ResponseEntity.ok().body(UserResponseDTO.userDtoToUser(userService.addUser(user)));
    }

     //US 0001
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity followUser(@PathVariable Integer userId, @PathVariable Integer userIdToFollow){
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

    //US 0003 e US 0008
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity followersList(@PathVariable Integer userId, @PathParam("order") String order){
        User user = userService.getUser(userId);
        return this.userService.getUserByTypeList(user, TypeUser.CLIENT, order);
    }

    //US 0004 e US 0008
    @GetMapping("{userId}/followed/list")
    public ResponseEntity followedList(@PathVariable Integer userId, @PathParam("order") String order){
        User user = userService.getUser(userId);
        return this.userService.getUserByTypeList(user, TypeUser.SELLER, order);
    }
}
