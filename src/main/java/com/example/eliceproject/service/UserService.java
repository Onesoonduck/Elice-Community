package com.example.eliceproject.service;


import com.example.eliceproject.dto.UserDTO;
import com.example.eliceproject.entity.User;

public interface UserService {

    Integer join(UserDTO userDTO);

    User create(String loginId, String username, String password);

}
