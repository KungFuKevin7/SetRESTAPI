package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.converter.DeckCardConverter;
import com.example.SetRESTAPI.api.dto.GameStateDto;
import com.example.SetRESTAPI.api.model.DeckCard;
import com.example.SetRESTAPI.api.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameStateService {

    @Autowired
    private FoundSetService foundSetService;
    @Autowired
    private CardsOnBoardService cardsOnBoardService;
    @Autowired
    private GameStatsDtoService gameStatsDtoService;
    @Autowired
    private DeckCardService deckCardService;

    public GameStateDto buildGameState(Game game, String newStatus) {

        List<DeckCard> deckCards = deckCardService.getDeckCardsInDeck(game);
        return new GameStateDto(
                game.getGame_id(),
                new DeckCardConverter().convertList(deckCards),
                newStatus,
                foundSetService.getFoundSets(game), //Check
                cardsOnBoardService.getCurrentCardsOnBoard(game.getGame_id()),
                gameStatsDtoService.getGameStatsDto(game.getGame_id())
        );

    }
}
