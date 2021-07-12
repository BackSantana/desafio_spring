package com.desafio_spring.demo.service.post;

import com.desafio_spring.demo.dto.post.PostPromoCountDTO;
import com.desafio_spring.demo.dto.post.PostResponseDTO;
import com.desafio_spring.demo.dto.post.PostsResponseDTO;
import com.desafio_spring.demo.dto.post.ProductDTO;
import com.desafio_spring.demo.exception.FutureDateException;
import com.desafio_spring.demo.exception.UserCannotRegisterPostException;
import com.desafio_spring.demo.model.post.Post;
import com.desafio_spring.demo.model.user.Seller;
import com.desafio_spring.demo.model.user.TypeUser;
import com.desafio_spring.demo.model.user.User;
import com.desafio_spring.demo.repository.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public ResponseEntity creatPost(User user, Post post){
        if(!isSeller(user))
            throw new UserCannotRegisterPostException(String.format("User [ %s ] cannot register post",  user));
        if(post.getDate().isAfter(LocalDate.now()))
            throw new FutureDateException(String.format("Future date [ %s ]", post.getDate()));

        Seller seller = (Seller) user;
        PostResponseDTO postResponseDTO;
        seller.getPosts().add(post);

        ProductDTO productDTO = ProductDTO.productToDTO(post.getProduct());

        if(post.getHasPromo() == false) {
            postResponseDTO = PostResponseDTO.postResponse(post, productDTO);
        } else {
            postResponseDTO = PostResponseDTO.postResponseHasPromo(post, productDTO);
        }

        return ResponseEntity.ok().body(postResponseDTO);
    }

    public ResponseEntity getListPostByUser(User user, String order){
        List<Seller> followSellers = postRepository.getUserByType(user.getFollowSellers());
        List<Post> pots = getPostsTwoWeekAgo(postRepository.addPosts(followSellers));
        List<PostResponseDTO> postsDTO = PostsResponseDTO.getPostsToPostsHasPromoDTO(pots);

        if(order != null && order.equals("date_asc"))
            postsDTO.sort(Comparator.comparing(PostResponseDTO::getDate));
        else if(order != null && order.equals("date_desc"))
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
    public ResponseEntity getPostsHasPromo(User user) {
        if(!isSeller(user))
            throw new UserCannotRegisterPostException(String.format("User [ %s ] cannot register post",  user));

        List<Post> posts = postRepository.getPostsHasPromo((Seller) user);
        List<PostResponseDTO> postsResponseDTO = PostsResponseDTO.getPostsToPostsHasPromoDTO(posts);

        return ResponseEntity.ok().body(PostsResponseDTO.postsToPotsDTO(postsResponseDTO, user.getId()));
    }

    public ResponseEntity countPostsPromo(User user){
        List<Post> posts = postRepository.getPostsHasPromo((Seller) user);
        return ResponseEntity.ok().body(PostPromoCountDTO.postPromoCountDTO(user, posts.size()));
    }

        public boolean isSeller(User user){
        if(user.getType().equals(TypeUser.SELLER))
            return true;
        return false;
    }
}
