package com.example.SetRESTAPI.api.controller;

import com.example.SetRESTAPI.api.authentication.JwtUtil;
import com.example.SetRESTAPI.api.dto.AuthRequest;
import com.example.SetRESTAPI.api.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;*/
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthenticationController {

 /*   private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final JwtUtil jwtUtil;

    public AuthenticationController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtUtil jwtService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtService;
    }*/
/*

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest){

        System.out.println("login");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getPlayername(), authRequest.getPassword()));
*/
/*
        return jwtUtil.generateToken(authRequest.getPlayername());*//*

    }
*/

}
