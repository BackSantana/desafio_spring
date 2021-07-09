package com.desafio_spring.demo.service.order;

import com.desafio_spring.demo.dto.post.PostResponseDTO;

import java.util.Comparator;

public class DateDescComparator implements Comparator<PostResponseDTO>{

    @Override
    public int compare(PostResponseDTO o1, PostResponseDTO o2) {
        return  o2.getDate().compareTo(o1.getDate());
    }
}
