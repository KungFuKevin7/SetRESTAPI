package com.example.SetRESTAPI.api.converter;

import com.example.SetRESTAPI.api.dto.DeckCardDto;
import com.example.SetRESTAPI.api.model.SetCard;

import java.util.ArrayList;
import java.util.List;

public class SetCardConverter implements IConverter<SetCard,DeckCardDto>{
    @Override
    public DeckCardDto convertObject(SetCard objectToConvert) {
        return new DeckCardDto(
                objectToConvert.getCard().getCard_id(),
                0,
                "",
                objectToConvert.getCard().getShape(),
                objectToConvert.getCard().getColour(),
                objectToConvert.getCard().getDisplayed_amount(),
                objectToConvert.getCard().getTexture(),
                objectToConvert.getCard().getCard_img()
        );
    }

    @Override
    public List<DeckCardDto> convertList(List<SetCard> objectsToConvert) {
        List<DeckCardDto> deckCardDtos = new ArrayList<>();
        for (SetCard objectToConvert : objectsToConvert) {
            deckCardDtos.add(convertObject(objectToConvert));
        }
        return deckCardDtos;
    }
}
