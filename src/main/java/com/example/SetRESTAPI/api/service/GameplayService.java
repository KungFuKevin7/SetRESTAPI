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
public class GameplayService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameStateService gameStateService;

    @Autowired
    private SetValidatorService setValidatorService;

    @Autowired
    private SetService setService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private GameProgressService gameProgressService;


    public GameStateDto validateSetAndUpdateBoard(int gameId, List<DeckCardDto> possibleSet) {
        Game game = gameRepository.findById((long) gameId).orElseThrow();

        boolean valid = setValidatorService.isValidSet(possibleSet);
        if (!valid) {
            return gameStateService.buildGameState(game, GameStatus.InProgress);
        }

        setService.processValidSet(game, possibleSet);

        boolean gameFinished = boardService.updateBoardWithCards(gameId); // Pulls new cards onto board

        if (gameFinished) {
            return gameProgressService.endGame(game);
        }

        return gameStateService.buildGameState(game, GameStatus.InProgress);
    }
}
