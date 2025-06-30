package com.example.SetRESTAPI.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class SetResponseDto {

    @Getter
    @Setter
    private boolean setValid;

    @Getter
    @Setter
    private List<List<DeckCardDto>> validatedSets;

    @Getter
    @Setter
    private List<DeckCardDto> boardCards;

    @Getter
    @Setter
    private List<DeckCardDto> deckCards;
}
