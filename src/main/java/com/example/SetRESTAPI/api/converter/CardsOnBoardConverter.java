package com.example.SetRESTAPI.api.converter;

import com.example.SetRESTAPI.api.dto.DeckCardDto;
import com.example.SetRESTAPI.api.model.Card;
import com.example.SetRESTAPI.api.model.CardsOnBoard;
import com.example.SetRESTAPI.api.model.DeckCard;
import com.example.SetRESTAPI.api.model.Game;

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

    public CardsOnBoard convertToCardsOnBoard(DeckCardDto objectsToConvert, int position, Game game) {

        CardsOnBoard cardsOnBoard = new CardsOnBoard();
        Card card = new DeckCardDtoConverter().convertObject(objectsToConvert);

        cardsOnBoard.setCard(card);
        cardsOnBoard.setGame(game);
        cardsOnBoard.setBoardPosition(position);
        return cardsOnBoard;
    }
}
