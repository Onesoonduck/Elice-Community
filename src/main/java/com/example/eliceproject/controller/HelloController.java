package com.example.eliceproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping(path = "/hello")
    public String hello() {
        return "hello";
    }
}
