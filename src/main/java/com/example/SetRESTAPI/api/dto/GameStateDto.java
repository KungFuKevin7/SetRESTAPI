package com.example.SetRESTAPI.api.dto;

import com.example.SetRESTAPI.api.model.Set;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class GameStateDto {

    @Getter
    @Setter
    private int gameId;

    @Getter
    @Setter
    private List<DeckCardDto> deckCards;

    @Getter
    @Setter
    private List<DeckCardDto> cardsOnBoard;

    @Getter
    @Setter
    private String status;

    @Getter
    @Setter
    private List<SetDto> foundSets;

    @Getter
    @Setter
    private GameStatsDto gameStats;


    public GameStateDto(int gameId, List<DeckCardDto> deckCards) {
        this.gameId = gameId;
        this.deckCards = deckCards;
    }

    public GameStateDto(int gameId, List<DeckCardDto> deckCards, List<DeckCardDto> cardsOnBoard, GameStatsDto gameStats) {
        this.gameId = gameId;
        this.deckCards = deckCards;
        this.cardsOnBoard = cardsOnBoard;
        this.gameStats = gameStats;
    }

    public GameStateDto(int gameId, List<DeckCardDto> deckCards, String status, List<SetDto> foundSets, List<DeckCardDto> cardsOnBoard, GameStatsDto gameStats) {
        this.gameId = gameId;
        this.deckCards = deckCards;
        this.status = status;
        this.foundSets = foundSets;
        this.cardsOnBoard = cardsOnBoard;
        this.gameStats = gameStats;
    }
}
