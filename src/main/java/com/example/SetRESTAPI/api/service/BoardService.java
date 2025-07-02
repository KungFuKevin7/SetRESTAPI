package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.converter.CardsOnBoardConverter;
import com.example.SetRESTAPI.api.converter.DeckCardConverter;
import com.example.SetRESTAPI.api.converter.DeckCardDtoConverter;
import com.example.SetRESTAPI.api.dto.DeckCardDto;
import com.example.SetRESTAPI.api.model.Card;
import com.example.SetRESTAPI.api.model.Game;
import com.example.SetRESTAPI.api.publics.CardStatus;
import com.example.SetRESTAPI.api.publics.GameStatus;
import com.example.SetRESTAPI.api.repository.CardsOnBoardRepository;
import com.example.SetRESTAPI.api.repository.DeckCardRepository;
import com.example.SetRESTAPI.api.repository.GameRepository;
import com.example.SetRESTAPI.logic.SetLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private CardsOnBoardService cardsOnBoardService;
    @Autowired
    private SetLogic setLogic;
    @Autowired
    private DeckCardRepository deckCardRepository;
    @Autowired
    private GameRepository gameRepository;

    public boolean updateBoardWithCards(int gameId){
        Game game = gameRepository.findById((long)gameId)
                .orElseThrow();

        //Get current State of cards/deck
        List<DeckCardDto> currentCardsOnBoard = cardsOnBoardService.getCurrentCardsOnBoard(gameId);
        List<DeckCardDto> cardsInDeck = new DeckCardConverter().convertList(deckCardRepository.getDeckCardsInDeckByGame(game.getGame_id()));

        //Try to pick new cards
        List<DeckCardDto> newCards = pickNewCards(cardsInDeck, currentCardsOnBoard);

        //Update Deck Cards to On table
        List<Integer> cardIds = new ArrayList<>();
        newCards.forEach(id -> cardIds.add(id.getCardId()));
        deckCardRepository.setDeckCardStatusToOnTable(cardIds.toArray(new Integer[0]), gameId);

        //If no cards could be picked, end the game
        if(newCards.isEmpty()){

            return true;
        }
        //Else add to the database
        else{
            cardsOnBoardService.addCardsOnBoard(newCards, game.getGame_id());

            return false;
        }

    }

    public List<DeckCardDto> pickNewCards(List<DeckCardDto> cardsInDeck, List<DeckCardDto> cardsOnBoard) {

        List<DeckCardDto> validNewCards = null;

        List<List<DeckCardDto>> possibleBoards = setLogic.generatePossibilities(cardsInDeck, 3);
        for (List<DeckCardDto> possibleBoard : possibleBoards){
            List<DeckCardDto> board = new ArrayList<>(cardsOnBoard);
            board.addAll(possibleBoard);

            board.forEach(card -> card.setStatus(CardStatus.onTable));

            List<Card> testCards = new DeckCardDtoConverter().convertList(board);

            if (!setLogic.FindSetOnTable(testCards.toArray(new Card[0])).isEmpty()){
                validNewCards = possibleBoard;
                break;
            }
        }
        if (validNewCards != null){
            return validNewCards;
        } else {
            return new ArrayList<>();
        }
    }
}
