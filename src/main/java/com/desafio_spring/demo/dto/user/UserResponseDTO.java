package com.desafio_spring.demo.dto.user;

import com.desafio_spring.demo.model.user.TypeUser;
import com.desafio_spring.demo.model.user.User;

public class UserResponseDTO {
    private Integer id;
    private String name;
    private TypeUser type;

    public UserResponseDTO(Integer id, String name, TypeUser type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
    public UserResponseDTO(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeUser getType() {
        return type;
    }

    public void setType(TypeUser type) {
        this.type = type;
    }

    public static UserResponseDTO userDtoToUser(User user){
        return new UserResponseDTO(user.getId(), user.getName(), user.getType());
    }
}
