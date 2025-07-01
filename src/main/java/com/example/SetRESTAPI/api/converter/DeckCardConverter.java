package com.example.SetRESTAPI.api.converter;

import com.example.SetRESTAPI.api.dto.DeckCardDto;
import com.example.SetRESTAPI.api.model.Card;
import com.example.SetRESTAPI.api.model.DeckCard;

import java.util.ArrayList;
import java.util.List;

public class DeckCardConverter implements IConverter<DeckCard, DeckCardDto> {

    @Override
    public DeckCardDto convertObject(DeckCard objectToConvert) {

        return new DeckCardDto(objectToConvert);
    }

    @Override
    public List<DeckCardDto> convertList(List<DeckCard> objects) {

        List<DeckCardDto> deckCardDtos = new ArrayList<>();

        for (DeckCard object : objects) {
            deckCardDtos.add(new DeckCardDto(object));
        }
        return deckCardDtos;
    }
}
