package com.example.SetRESTAPI.api.controller;


import com.example.SetRESTAPI.api.model.Users;
import com.example.SetRESTAPI.api.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Users> getAllUsers(HttpServletRequest request){
        return userService.getAllUsers();
    }

    @GetMapping("/get-csrf")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping("/{id}")
    public Optional<Users> getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping("/register")
    public Users addUser(@RequestBody Users users){
        return userService.addUser(users);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users  user){
        System.out.println(user.getUsername());
        return "Login fired";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Users> deletePlayer(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
