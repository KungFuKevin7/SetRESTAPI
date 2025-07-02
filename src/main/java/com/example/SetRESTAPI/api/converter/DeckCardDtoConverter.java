package com.example.SetRESTAPI.api.converter;

import com.example.SetRESTAPI.api.dto.DeckCardDto;
import com.example.SetRESTAPI.api.model.Card;
import com.example.SetRESTAPI.api.model.DeckCard;
import com.example.SetRESTAPI.api.model.Set;
import com.example.SetRESTAPI.api.model.SetCard;

import java.util.ArrayList;
import java.util.List;

public class DeckCardDtoConverter implements IConverter<DeckCardDto, Card> {

    @Override
    public Card convertObject(DeckCardDto objectToConvert) {
        Card card = new Card();
        card.setCard_id(objectToConvert.getCardId());
        card.setColour(objectToConvert.getColour());
        card.setTexture(objectToConvert.getTexture());
        card.setDisplayed_amount(objectToConvert.getDisplayed_amount());
        card.setCard_img(objectToConvert.getCard_img());
        card.setShape(objectToConvert.getShape());

        return card;
    }

    @Override
    public List<Card> convertList(List<DeckCardDto> objectsToConvert) {
        List<Card> cards = new ArrayList<>();
        for (DeckCardDto card : objectsToConvert){
            cards.add(convertCard(card));
        }
        return cards;
    }

    public Card convertCard(DeckCardDto objectToConvert) {
        Card card = new Card();
        card.setCard_id(objectToConvert.getCardId());
        card.setCard_img(objectToConvert.getCard_img());
        card.setTexture(objectToConvert.getTexture());
        card.setColour(objectToConvert.getColour());
        card.setShape(objectToConvert.getShape());
        card.setDisplayed_amount(objectToConvert.getDisplayed_amount());
        return card;
    }

    public List<Card> convertCardList(List<DeckCardDto> objectsToConvert) {
        List<Card> cards = new ArrayList<>();
        for (DeckCardDto card : objectsToConvert){
            cards.add(convertCard(card));
        }

        return cards;
    }

    public SetCard convertSetCard(DeckCardDto card, Set set) {
        SetCard setCard = new SetCard();
        setCard.setCard(convertCard(card));
        setCard.setSet(set);
        return setCard;
    }

    public List<SetCard> convertSetCardList(List<DeckCardDto> cards, Set set) {
        List<SetCard> setCards = new ArrayList<>();
        for (DeckCardDto card : cards){
            setCards.add(convertSetCard(card, set));
        }
        return setCards;
    }
}
