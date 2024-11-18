package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.model.Player;
import com.example.SetRESTAPI.api.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    public Optional<Player> getPlayerById(Long id){
        return playerRepository.findById(id);
    }

    public Player addPlayer(Player player){
        return playerRepository.save(player);
    }

    public void deletePlayer(Long id){
        if (playerRepository.existsById(id)){
            playerRepository.deleteById(id);
        } else{
            throw new RuntimeException("Player does not exist");
        }
    }

}
