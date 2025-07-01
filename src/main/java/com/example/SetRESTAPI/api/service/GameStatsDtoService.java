package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.dto.GameStateDto;
import com.example.SetRESTAPI.api.dto.GameStatsDto;
import com.example.SetRESTAPI.api.model.*;
import com.example.SetRESTAPI.api.repository.CardsOnBoardRepository;
import com.example.SetRESTAPI.api.repository.GameRepository;
import com.example.SetRESTAPI.api.repository.SetRepository;
import com.example.SetRESTAPI.logic.SetLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameStatsDtoService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private DeckCardService deckCardService;

    @Autowired
    private SetRepository setRepository;

    @Autowired
    private SetLogic setLogic;

    @Autowired
    private CardsOnBoardRepository cardsOnBoardRepository;

    public int getFoundSetsByGame(Game game){
        List<Set> foundSets = setRepository.findByGame(game);
        return foundSets.size();
    }

    public int getRemainingCardsInDeckByGame(Game game){
        List<DeckCard> deckCards = deckCardService.getDeckCardsInDeck(game);

        return deckCards.size();
    }

    public int getPossibleSetsOnTableByGame(Game game){

        List<CardsOnBoard> cardsOnBoard = cardsOnBoardRepository.getCardsOnBoardByGame(
                game, Sort.by(Sort.Direction.ASC, "boardPosition"));

        List<Card> cards = new ArrayList<>();

        for (CardsOnBoard cardsOnBoardItem : cardsOnBoard) {
            cards.add(cardsOnBoardItem.getCard());
        }

        List<List<Card>> possibleSets = setLogic.FindSetOnTable(cards.toArray(new Card[0]));

        return possibleSets.size();
    }

    public GameStatsDto getGameStatsDto(int gameId){
        Game currentGame = gameRepository.findById((long)gameId)
                .orElseThrow();

        GameStatsDto gameStatsDto = new GameStatsDto();
        gameStatsDto.setFoundSetsInGame(getFoundSetsByGame(currentGame));
        gameStatsDto.setPossibleSetsOnTable(getPossibleSetsOnTableByGame(currentGame));
        gameStatsDto.setCardsRemainingInDeck(getRemainingCardsInDeckByGame(currentGame));
        return gameStatsDto;
    }

}
