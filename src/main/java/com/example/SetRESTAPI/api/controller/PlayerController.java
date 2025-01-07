package com.example.SetRESTAPI.api.controller;


import com.example.SetRESTAPI.api.dto.AuthRequest;
import com.example.SetRESTAPI.api.model.Player;
import com.example.SetRESTAPI.api.service.PlayerService;
import com.example.SetRESTAPI.api.service.SetService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
/*import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;*/
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/player")
@CrossOrigin("http://localhost:2020")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public List<Player> getAllPlayers(HttpServletRequest request){
        return playerService.getAllPlayers();
    }

    @GetMapping("/{id}")
    public Optional<Player> getPlayerById(@PathVariable Long id){
        return playerService.getPlayerById(id);
    }

    @PostMapping("/register")
    public Player addPlayer(@RequestBody Player player){
        return playerService.addPlayer(player);
    }

    @PostMapping("/login")
    public String login(@RequestBody Player player){
        System.out.println(player.getPlayer_name());
        return "Login fired";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Player> deletePlayer(@PathVariable Long id){
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }
}
