package com.example.SetRESTAPI.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class GameInitDto {

    @Getter
    @Setter
    private int gameId;

    @Getter
    @Setter
    private List<DeckCardDto> deckCards;

    @Getter
    @Setter
    private List<DeckCardDto> cardsOnBoard;

    public GameInitDto(int gameId, List<DeckCardDto> deckCards) {
        this.gameId = gameId;
        this.deckCards = deckCards;
    }

    public GameInitDto(int gameId, List<DeckCardDto> deckCards, List<DeckCardDto> cardsOnBoard) {
        this.gameId = gameId;
        this.deckCards = deckCards;
        this.cardsOnBoard = cardsOnBoard;
    }
}
