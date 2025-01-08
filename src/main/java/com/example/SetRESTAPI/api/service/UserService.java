package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.model.Users;
import com.example.SetRESTAPI.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//User Details Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);

    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<Users> getUserById(int id){
        return userRepository.findById(id);
    }

    public Users register(Users users){

        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        return userRepository.save(users);
    }

    public void deleteUser(int id){
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
        } else{
            throw new RuntimeException("User does not exist");
        }
    }

}
