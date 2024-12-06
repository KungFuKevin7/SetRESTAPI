package com.example.SetRESTAPI.api.controller;

import com.example.SetRESTAPI.api.authentication.JwtUtil;
import com.example.SetRESTAPI.api.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private JwtUtil jwtService;


}
