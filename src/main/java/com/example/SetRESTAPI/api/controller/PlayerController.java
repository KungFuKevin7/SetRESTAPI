package com.example.SetRESTAPI.api.controller;


import com.example.SetRESTAPI.api.model.Player;
import com.example.SetRESTAPI.api.service.PlayerService;
import com.example.SetRESTAPI.api.service.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public List<Player> getAllPlayers(){
        return playerService.getAllPlayers();
    }

    @GetMapping("/{id}")
    public Optional<Player> getPlayerById(@PathVariable Long id){
        return playerService.getPlayerById(id);
    }

    @PostMapping
    public Player addPlayer(@RequestBody Player player){
        return playerService.addPlayer(player);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Player> deletePlayer(@PathVariable Long id){
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }
}
