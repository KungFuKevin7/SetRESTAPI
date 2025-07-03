package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.dto.DeckCardDto;
import com.example.SetRESTAPI.api.dto.GameStateDto;
import com.example.SetRESTAPI.api.model.Game;
import com.example.SetRESTAPI.api.publics.GameStatus;
import com.example.SetRESTAPI.api.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameProgressService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameStateService gameStateService;

    public GameStateDto endGame(Game game){
        gameRepository.updateGameStatusByGameId
                (GameStatus.Completed, game.getGame_id());

        return gameStateService.buildGameState(game,
                GameStatus.Completed);
    }
}
