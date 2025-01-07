package com.example.SetRESTAPI.api.controller;


import com.example.SetRESTAPI.api.model.Player;
import com.example.SetRESTAPI.api.service.PlayerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
/*import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;*/
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/player")
@CrossOrigin
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public List<Player> getAllPlayers(HttpServletRequest request){
        return playerService.getAllPlayers();
    }

    @GetMapping("/get-csrf")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping("/{id}")
    public Optional<Player> getPlayerById(@PathVariable Long id){
        return playerService.getPlayerById(id);
    }

    @PostMapping("/register")
    public Player addPlayer(@RequestBody Player players){
        return playerService.addPlayer(players);
    }

    @PostMapping("/login")
    public String login(@RequestBody Player players){
        System.out.println(players.getPlayer_name());
        return "Login fired";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Player> deletePlayer(@PathVariable Long id){
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }
}
