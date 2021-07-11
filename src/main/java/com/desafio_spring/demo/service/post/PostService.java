package com.desafio_spring.demo.service.post;

import com.desafio_spring.demo.dto.post.PostResponseDTO;
import com.desafio_spring.demo.dto.post.PostsResponseDTO;
import com.desafio_spring.demo.dto.post.ProductDTO;
import com.desafio_spring.demo.exception.FutureDateException;
import com.desafio_spring.demo.exception.UserCannotRegisterPostException;
import com.desafio_spring.demo.model.post.Post;
import com.desafio_spring.demo.model.user.FollowSeller;
import com.desafio_spring.demo.model.user.Seller;
import com.desafio_spring.demo.model.user.TypeUser;
import com.desafio_spring.demo.model.user.User;
import com.desafio_spring.demo.repository.user.UserRepository;
import com.desafio_spring.demo.service.order.DateDescComparator;
import com.desafio_spring.demo.service.user.UserService;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    UserRepository userRepository;

    public ResponseEntity creatPost(User user, Post post){
        if(!isSeller(user))
            throw new UserCannotRegisterPostException(String.format("User [ %s ] cannot register post",  user));
        if(post.getDate().isAfter(LocalDate.now()))
            throw new FutureDateException(String.format("Future date [ %s ]", post.getDate()));

        Seller seller = (Seller) user;
        seller.getPosts().add(post);
        ProductDTO productDTO = ProductDTO.productToDTO(post.getProduct());

        return ResponseEntity.ok().body(PostResponseDTO.postResponse(post, productDTO));
    }

    public ResponseEntity getListPostByUser(User user, String order){
        List<Post> posts = new ArrayList<>();
        List<Seller> followSellers = getUserByType(user.getFollowSellers());

        followSellers.forEach(f -> f.getPosts().forEach( post -> posts.add(post)));

        List<Post> pots = getPostsTwoWeekAgo(posts);
        List<PostResponseDTO> postsDTO = PostsResponseDTO.getPostsToPostsResponseDTO(pots);


        if(order.equals("name_asc"))
            postsDTO.sort(Comparator.comparing(PostResponseDTO::getDate));
        else if(order.equals("name_desc"))
            postsDTO.sort(Comparator.comparing(PostResponseDTO::getDate).reversed());

        return ResponseEntity.ok().body(PostsResponseDTO.postsToPotsDTO(postsDTO, user.getId()));
    }

    private List<Post> getPostsTwoWeekAgo(List<Post> posts) {
        LocalDate twoWeekAgo = LocalDate.now().minusDays(14);

        return posts.stream()
                .filter(post -> post.getDate().isAfter(twoWeekAgo))
                .filter(post -> post.getDate().isBefore(LocalDate.now()))
                .collect(Collectors.toList());
    }

    public boolean isSeller(User user){
        if(user.getType().equals(TypeUser.SELLER))
            return true;
        return false;
    }

    public List<Seller> getUserByType(List<FollowSeller> followSellers){
        List<Seller> sellers = new ArrayList<>();
        followSellers.stream().forEach(f -> sellers.add((Seller) userRepository.getUser(f.getUser_id())));
        return sellers;
    }
}
