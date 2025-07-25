package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.converter.*;
import com.example.SetRESTAPI.api.dto.DeckCardDto;
import com.example.SetRESTAPI.api.dto.GameStateDto;
import com.example.SetRESTAPI.api.dto.GameStatsDto;
import com.example.SetRESTAPI.api.model.*;
import com.example.SetRESTAPI.api.publics.CardStatus;
import com.example.SetRESTAPI.api.publics.GameStatus;
import com.example.SetRESTAPI.api.repository.CardsOnBoardRepository;
import com.example.SetRESTAPI.api.repository.DeckCardRepository;
import com.example.SetRESTAPI.api.repository.GameRepository;
import com.example.SetRESTAPI.api.repository.UserRepository;
import com.example.SetRESTAPI.logic.SetLogic;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private DeckCardRepository deckCardRepository;
    @Autowired
    private CardsOnBoardRepository cardsOnBoardRepository;
    @Autowired
    private SetLogic setLogic;
    @Autowired
    private GameStateService gameStateService;
    @Autowired
    private DeckService deckService;


    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public List<Game> getGamesByUser(UserPrincipal user) {
        return gameRepository.findByUsers_Userid(user.getUserId(), Sort.by(Sort.Order.desc("status")));
    }

    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    public void deleteGame(Long id){
        if (gameRepository.existsById(id)){
            gameRepository.deleteById(id);
        } else{
            throw new RuntimeException("Game does not exist");
        }
    }

    public Game startNewGame(Users user) {
        Game game = new Game();
        game.setUsers(user);
        game.setStatus(GameStatus.InProgress);
        game.setCreated_at(LocalDateTime.now());
        return gameRepository.save(game);
    }

    @Transactional
    public GameStateDto startGameWithDeck(Users user) {
        Game game = startNewGame(user);

        //Shuffled Deck
        List<Card> fullDeck;

        List<DeckCard> deckCards = new ArrayList<>();
        List<CardsOnBoard> cardsOnBoards = new ArrayList<>();

        List<Card> cardsOnBoard = new ArrayList<>();
        List<DeckCard> tableCards;

        do {
            fullDeck = deckService.getShuffledDeck();

            for (int i = 0; i < fullDeck.size(); i++) {
                DeckCard deckCard = new DeckCard();
                deckCard.setPosition(i);
                deckCard.setCard(fullDeck.get(i));
                deckCard.setGame(game);
                deckCard.setStatus(CardStatus.inCardDeck);

                deckCards.add(deckCard);
            }

            //Get Table
            tableCards = deckCards.subList(0, 12);

            cardsOnBoard = new DeckCardToCardConverter().convertList(tableCards);

        } while(setLogic.FindSetOnTable(
                cardsOnBoard.toArray(new Card[0])).isEmpty());

        //Remaining Deck
        List<DeckCard> cardsInDeck = deckCards.subList(12, deckCards.size());

        for (DeckCard deckCard : deckCards) {
            for (DeckCard cardOnTable : tableCards) {
                if (deckCard.getCard().equals(cardOnTable.getCard())) {
                    deckCard.setStatus(CardStatus.onTable);
                }
            }
        }

        for (int i = 0; i < tableCards.size(); i++) {
            DeckCard tableCard = tableCards.get(i);
            CardsOnBoard cardOnBoard = new CardsOnBoard();
            cardOnBoard.setBoardPosition(i);
            cardOnBoard.setGame(game);
            cardOnBoard.setCard(tableCard.getCard());
            cardsOnBoards.add(cardOnBoard);
        }

        List<DeckCardDto> deckCardDto = cardsInDeck
                .stream()
                .map(DeckCardDto::new)
                .toList();

        List<DeckCardDto> tableCardDto = tableCards
                .stream()
                .map(DeckCardDto::new)
                .toList();

        deckCardRepository.saveAll(deckCards);
        cardsOnBoardRepository.saveAll(cardsOnBoards);

        return gameStateService.startGame(game);
    }

    public GameStateDto startGame(Long gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow();

        return gameStateService.startGame(game);
    }

}