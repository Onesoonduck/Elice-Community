package com.example.eliceproject.service;

import com.example.eliceproject.dto.UserDTO;
import com.example.eliceproject.entity.User;
import com.example.eliceproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Integer join(UserDTO userDTO) {
        User user = User.builder()
                .loginId(userDTO.getLoginId())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword1())
                .build();

        return userRepository.save(user).getId();
    }

    public User create (String loginId, String username, String password) {
        User user = new User();
        user.setLoginId(loginId);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(user);

        return user;
    }

}
