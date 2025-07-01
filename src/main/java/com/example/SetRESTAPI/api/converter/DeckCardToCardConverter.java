package com.example.SetRESTAPI.api.converter;

import com.example.SetRESTAPI.api.model.Card;
import com.example.SetRESTAPI.api.model.DeckCard;

import java.util.ArrayList;
import java.util.List;

public class DeckCardToCardConverter implements IConverter<DeckCard, Card> {

    @Override
    public Card convertObject(DeckCard objectToConvert) {
        return objectToConvert.getCard();
    }

    @Override
    public List<Card> convertList(List<DeckCard> objectsToConvert) {
        List<Card> cards = new ArrayList<>();
        for (DeckCard objectToConvert : objectsToConvert) {
            cards.add(convertObject(objectToConvert));
        }

        return cards;
    }
}
