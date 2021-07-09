package com.desafio_spring.demo.controller;

import com.desafio_spring.demo.dto.post.PostRequestDTO;
import com.desafio_spring.demo.model.post.Post;
import com.desafio_spring.demo.model.user.User;
import com.desafio_spring.demo.service.post.PostService;
import com.desafio_spring.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/products")
@RestController
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @PostMapping("/newpost")
    public ResponseEntity creatPost(@RequestBody PostRequestDTO requestDTO){
        Post post = PostRequestDTO.DtoToPost(requestDTO);
        User user = userService.getUser(requestDTO.getUser_id());
        return postService.creatPost(user, post);
    }
}