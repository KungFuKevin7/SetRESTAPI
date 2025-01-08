package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.model.Users;
import com.example.SetRESTAPI.api.model.UserPrincipal;
import com.example.SetRESTAPI.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        //System.out.println("Login attempt by: " + userName);

        Users user = userRepository.findByUsername(userName);

        if (user == null) {
            throw new UsernameNotFoundException("Players " + userName + " not found.");
        }

        return new UserPrincipal(user);


    }


}
