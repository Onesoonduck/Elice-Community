package com.example.eliceproject.controller;

import com.example.eliceproject.dto.UserDTO;
import com.example.eliceproject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("/users/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/main")
    public String main () {
        return "board/main";
    }

    @GetMapping("/signup")
    public String signup (Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "board/signup";
    }

    @PostMapping("/signup")
    public String signup (@Valid UserDTO userDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "board/signup";
        }

        if (!userDTO.getPassword1().equals(userDTO.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다.");
            return "board/signup";
        }

        userService.create(userDTO.getLoginId(), userDTO.getUsername(), userDTO.getPassword1());

        return "redirect:/users/main";
    }

}
