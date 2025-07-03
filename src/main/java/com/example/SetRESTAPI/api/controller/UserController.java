package com.example.SetRESTAPI.api.controller;


import com.example.SetRESTAPI.api.dto.AuthTokenDto;
import com.example.SetRESTAPI.api.model.Users;
import com.example.SetRESTAPI.api.service.JwtService;
import com.example.SetRESTAPI.api.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @GetMapping
    public List<Users> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/get-csrf")
    public CsrfToken getCsrfToken(HttpServletRequest request){

        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping("/{id}")
    public Optional<Users> getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PostMapping("/register")
    public Users register(@RequestBody Users user){
        return userService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthTokenDto> login(@RequestBody Users user){

        //Verify user and create Jwt
        Users loggedIn = userService.getUserByUsername(user.getUsername());
        if (loggedIn != null) {
            user.setUserid(loggedIn.getUserid());
            String token = userService.verify(user);
            long timeRemaining = userService.getRemainingTime(token);

            //Convert to Dto
            AuthTokenDto authTokenDto = new AuthTokenDto(
                    user.getUsername(),
                    token,
                    timeRemaining);

            return ResponseEntity.ok(authTokenDto);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Users> deletePlayer(@PathVariable int id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
