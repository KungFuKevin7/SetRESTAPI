package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.dto.UserFormDto;
import com.example.SetRESTAPI.api.model.Player;
import com.example.SetRESTAPI.api.repository.PlayerRepository;
import org.apache.catalina.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final PlayerRepository playerRepository;

    private final AuthenticationManager authenticationManager;
    //private final PasswordEncoder passwordEncoder;

    public AuthenticationService(PlayerRepository playerRepository, AuthenticationManager authenticationManager) {
        this.playerRepository = playerRepository;
        this.authenticationManager = authenticationManager;
    }

    public Player Register(UserFormDto playerRegister) {
        Player player = new Player();
        player.setPlayerName(playerRegister.getPlayerName());

        return playerRepository.save(player);
    }

    public User authenticate(UserFormDto playerLogin) {

        String playerName = playerLogin.getPlayerName();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        playerName,
                        playerLogin.getPassword()
                )
        );

        return (User) playerRepository
                .findByPlayerName(playerLogin.getPlayerName())
                .orElseThrow();
    }
}
