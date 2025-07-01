package com.example.SetRESTAPI.api.converter;

import com.example.SetRESTAPI.api.dto.DeckCardDto;
import com.example.SetRESTAPI.api.model.CardsOnBoard;

import java.util.ArrayList;
import java.util.List;

public class CardsOnBoardConverter implements IConverter<CardsOnBoard, DeckCardDto> {

    @Override
    public DeckCardDto convertObject(CardsOnBoard objectToConvert) {
        return new CardConverter().convertObject(objectToConvert.getCard());
    }

    @Override
    public List<DeckCardDto> convertList(List<CardsOnBoard> objectsToConvert) {
        List<DeckCardDto> deckCardDtos = new ArrayList<>();

        for (CardsOnBoard objectToConvert : objectsToConvert) {
            deckCardDtos.add(convertObject(objectToConvert));
        }

        return deckCardDtos;
    }
}
