package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Поиск пользователей по имени
    @GetMapping("/name/{name}")
    public List<User> getUsersByName(@PathVariable String name) {
        return userService.findUsersByName(name); // Изменено на findUsersByName
    }

    // Поиск пользователей по домену email
    @GetMapping("/email-domain/{domain}")
    public List<User> getUsersByEmailDomain(@PathVariable String domain) {
        return userService.findUsersByEmailDomain(domain); // Изменено на findUsersByEmailDomain
    }
}