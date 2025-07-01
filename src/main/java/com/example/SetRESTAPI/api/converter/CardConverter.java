package com.example.SetRESTAPI.api.converter;

import com.example.SetRESTAPI.api.dto.DeckCardDto;
import com.example.SetRESTAPI.api.model.Card;

import java.util.ArrayList;
import java.util.List;

public class CardConverter implements IConverter<Card, DeckCardDto> {

    @Override
    public DeckCardDto convertObject(Card objectToConvert) {
        return new DeckCardDto(
                objectToConvert.getCard_id(),
                0,
                "",
                objectToConvert.getShape(),
                objectToConvert.getColour(),
                objectToConvert.getDisplayed_amount(),
                objectToConvert.getTexture(),
                objectToConvert.getCard_img()
        );
    }

    @Override
    public List<DeckCardDto> convertList(List<Card> objectsToConvert) {
        List<DeckCardDto> deckCardDtos = new ArrayList<>();
        for (Card objectToConvert : objectsToConvert) {
            deckCardDtos.add(convertObject(objectToConvert));
        }
        return deckCardDtos;
    }
}
