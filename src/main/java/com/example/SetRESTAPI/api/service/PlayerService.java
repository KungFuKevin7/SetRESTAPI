package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.model.Player;
import com.example.SetRESTAPI.api.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;*/
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PlayerRepository playerRepository;
/*

    @Autowired
    AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

*/


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
