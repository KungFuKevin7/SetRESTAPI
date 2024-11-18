package com.example.SetRESTAPI.api.service;

import aj.org.objectweb.asm.commons.Remapper;
import com.example.SetRESTAPI.api.model.Game;
import com.example.SetRESTAPI.api.model.Set;
import com.example.SetRESTAPI.api.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
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

}