package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.model.Player;
import com.example.SetRESTAPI.api.model.UserPrincipal;
import com.example.SetRESTAPI.api.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public UserDetails loadUserByUsername(String playerName) throws UsernameNotFoundException {

        System.out.println("loadUserByUsername: " + playerName);

        Player player = playerRepository.findByPlayer_name(playerName);

        if (player == null) {
            throw new UsernameNotFoundException("Players " + playerName + " not found.");
        }

        return new UserPrincipal(player);
    }


}
