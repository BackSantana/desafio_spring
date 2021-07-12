package com.desafio_spring.demo.dto.user;

import com.desafio_spring.demo.Utils.GenerateID;
import com.desafio_spring.demo.model.user.*;

import java.util.List;

public class UserRequestDTO {
    private String name;
    private String type;

    public UserRequestDTO(String name, String type) {
        this.name = name;
        this.type = type;
    }
    public UserRequestDTO(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static User userDtoToSeller(UserRequestDTO userDTO){
        List<FollowSeller> usersFollowing = null;
        return new Seller(GenerateID.getLastId(), userDTO.getName(), usersFollowing);
    }

    public static User userDtoToClient(UserRequestDTO userDTO){
        List<FollowSeller> usersFollowing = null;
        return new Client(GenerateID.getLastId(), userDTO.getName(), usersFollowing);
    }
}
