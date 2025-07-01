package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.converter.CardConverter;
import com.example.SetRESTAPI.api.dto.DeckCardDto;
import com.example.SetRESTAPI.api.model.Card;
import com.example.SetRESTAPI.api.model.CardsOnBoard;
import com.example.SetRESTAPI.api.model.Game;
import com.example.SetRESTAPI.logic.SetLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HintService {

    @Autowired
    private GameService gameService;

    @Autowired
    private CardsOnBoardService cardsOnBoardService;

    @Autowired
    private SetLogic setLogic;

    public List<DeckCardDto> getHint(int gameId) {

        Game currentGame = gameService.getGameById((long)gameId).
                orElseThrow();

        List<CardsOnBoard> currentCardsOnBoard =
                cardsOnBoardService.getCardsOnBoardByGame(currentGame);

        List<Card> cards = new ArrayList<>();

        for (CardsOnBoard cardsOnBoard : currentCardsOnBoard) {
            cards.add(cardsOnBoard.getCard());
        }

        List<Card> hintedCards =
                setLogic.getSetHint(cards.toArray(Card[]::new));


        return new CardConverter().convertList(hintedCards);

    }
}
