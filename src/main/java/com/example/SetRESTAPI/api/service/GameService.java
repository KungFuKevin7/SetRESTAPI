package com.example.SetRESTAPI.api.service;

import aj.org.objectweb.asm.commons.Remapper;
import com.example.SetRESTAPI.api.model.Game;
import com.example.SetRESTAPI.api.model.Set;
import com.example.SetRESTAPI.api.model.UserPrincipal;
import com.example.SetRESTAPI.api.model.Users;
import com.example.SetRESTAPI.api.repository.GameRepository;
import com.example.SetRESTAPI.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    @Autowired
    public GameService(GameRepository gameRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public List<Game> getGamesByUser(UserPrincipal user) {
        return gameRepository.findByUsers_Userid(user.getUserId());
    }

    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    public Game addGame(Game game){
        return gameRepository.save(game);
    }

    public void deleteGame(Long id){
        if (gameRepository.existsById(id)){
            gameRepository.deleteById(id);
        } else{
            throw new RuntimeException("Game does not exist");
        }
    }

    public Game startGame(Users user)
    {
        Game game = new Game();
        game.setElapsedTime(0);
        game.setUsers(user);
        game.setSetsFound(0);
        return gameRepository.save(game);
    }
}