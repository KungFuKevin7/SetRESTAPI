package com.example.SetRESTAPI.api.dto;

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
    private GameStatsDto gameStats;

/*    @Getter
    @Setter
    private List<List<DeckCardDto>> possibleSets;*/

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
}
