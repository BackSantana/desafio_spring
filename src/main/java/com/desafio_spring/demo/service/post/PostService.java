package com.desafio_spring.demo.service.post;

import com.desafio_spring.demo.dto.post.PostResponseDTO;
import com.desafio_spring.demo.dto.post.ProductDTO;
import com.desafio_spring.demo.exception.UserCannotRegisterPostException;
import com.desafio_spring.demo.model.post.Post;
import com.desafio_spring.demo.model.user.Seller;
import com.desafio_spring.demo.model.user.TypeUser;
import com.desafio_spring.demo.model.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    public ResponseEntity creatPost(User user, Post post){
        if(!isSeller(user))
            throw new UserCannotRegisterPostException(String.format("User [%s] cannot register post",  user));

        Seller seller = (Seller) user;
        seller.getPosts().add(post);
        ProductDTO productDTO = ProductDTO.productToDTO(post.getProduct());

        return ResponseEntity.ok().body(PostResponseDTO.postResponse(post, productDTO));
    }


    public boolean isSeller(User user){
        if(user.getType().equals(TypeUser.SELLER))
            return true;
        return false;
    }
}
