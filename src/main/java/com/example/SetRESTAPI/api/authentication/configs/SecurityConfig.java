package com.example.SetRESTAPI.api.authentication.configs;

import com.example.SetRESTAPI.api.repository.PlayerRepository;

public class SecurityConfig{
    private final PlayerRepository playerRepository;

    public SecurityConfig(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }


}
