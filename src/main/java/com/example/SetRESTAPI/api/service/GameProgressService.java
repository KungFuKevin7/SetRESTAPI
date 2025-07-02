package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.dto.DeckCardDto;
import com.example.SetRESTAPI.api.publics.GameStatus;
import com.example.SetRESTAPI.api.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameProgressService {

    @Autowired
    private GameRepository gameRepository;

    public String determineStatus(List<DeckCardDto> newCards){
        if (newCards.isEmpty()){
            return GameStatus.Completed;
        }
        else {
            return GameStatus.InProgress;
        }
    }

    public void endGame(int gameId){
        gameRepository.updateGameStatusByGameId
                (GameStatus.Completed, gameId);
    }
}
