package com.example.SetRESTAPI.api.dto;

import lombok.Getter;
import lombok.Setter;

public class GameStatsDto {

    @Getter
    @Setter
    private int cardsRemainingInDeck;

    @Getter
    @Setter
    private int possibleSetsOnTable;

    @Getter
    @Setter
    private int foundSetsInGame;
}
