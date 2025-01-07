package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.model.Player;
import com.example.SetRESTAPI.api.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PlayerRepository playerRepository;


    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    public Optional<Player> getPlayerById(Long id){
        return playerRepository.findById(id);
    }

    public Player addPlayer(Player player){
        //player.setPassword(passwordEncoder.encode(player.getPassword()));
        return playerRepository.save(player);
    }

/*    public String verify(Player player){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(player.getUsername(), player.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken();
        } else {
            throw new RuntimeException("Authentication failed");
        }
    }*/

    public void deletePlayer(Long id){
        if (playerRepository.existsById(id)){
            playerRepository.deleteById(id);
        } else{
            throw new RuntimeException("Player does not exist");
        }
    }

}
