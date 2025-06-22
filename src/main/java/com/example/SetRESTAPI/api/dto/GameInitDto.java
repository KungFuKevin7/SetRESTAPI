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

    public GameInitDto(int gameId, List<DeckCardDto> deckCards) {
        this.gameId = gameId;
        this.deckCards = deckCards;
    }

}
