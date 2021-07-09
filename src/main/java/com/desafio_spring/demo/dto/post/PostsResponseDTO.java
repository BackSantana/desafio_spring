package com.desafio_spring.demo.dto.post;

import com.desafio_spring.demo.model.post.Post;

import java.util.ArrayList;
import java.util.List;

public class PostsResponseDTO {
    private Integer user_id;
    private List<PostResponseDTO> postsResponseDTO;

    public PostsResponseDTO(Integer user_id, List<PostResponseDTO> postsResponseDTO) {
        this.user_id = user_id;
        this.postsResponseDTO = postsResponseDTO;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public List<PostResponseDTO> getPostResponseDTO() {
        return postsResponseDTO;
    }

    public static PostsResponseDTO postsToPotsDTO(List<PostResponseDTO> posts, Integer user_id){
        return new PostsResponseDTO(user_id, posts);
    }

    public static List<PostResponseDTO> getPostsToPostsResponseDTO(List<Post> posts){
        List<PostResponseDTO> postsResponseDTO = new ArrayList<>();

        posts.forEach(post -> postsResponseDTO.add(PostResponseDTO.postResponseToList(post, ProductDTO.productToDTO(post.getProduct()))));
        return postsResponseDTO;
    }
}
