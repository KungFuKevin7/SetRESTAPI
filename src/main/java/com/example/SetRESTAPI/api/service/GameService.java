package com.example.SetRESTAPI.api.service;

import aj.org.objectweb.asm.commons.Remapper;
import com.example.SetRESTAPI.api.dto.DeckCardDto;
import com.example.SetRESTAPI.api.dto.GameInitDto;
import com.example.SetRESTAPI.api.model.*;
import com.example.SetRESTAPI.api.publics.GameStatus;
import com.example.SetRESTAPI.api.repository.CardsOnBoardRepository;
import com.example.SetRESTAPI.api.repository.DeckCardRepository;
import com.example.SetRESTAPI.api.repository.GameRepository;
import com.example.SetRESTAPI.api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final CardService cardService;
    private final DeckCardRepository deckCardRepository;
    private final CardsOnBoardRepository cardsOnBoardRepository;

    @Autowired
    public GameService(GameRepository gameRepository, UserRepository userRepository, CardService cardService, DeckCardRepository deckCardRepository, CardsOnBoardRepository cardsOnBoardRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.cardService = cardService;
        this.deckCardRepository = deckCardRepository;
        this.cardsOnBoardRepository = cardsOnBoardRepository;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public List<Game> getGamesByUser(UserPrincipal user) {
        return gameRepository.findByUsers_Userid(user.getUserId(), Sort.by(Sort.Order.desc("status")));
    }

    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    public Game addGame(Game game){
        return gameRepository.save(game);
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
        game.setElapsed_time(0);
        game.setUsers(user);
        game.setStatus(GameStatus.InProgress);
        game.setSets_found(0);
        game.setCreated_at(LocalDateTime.now());
        return gameRepository.save(game);
    }

    @Transactional
    public GameInitDto startGameWithDeck(Users user) {
        Game game = startNewGame(user);

        List<Card> playingCards = cardService.getAllCardsShuffled();

        List<DeckCard> deckCards = new ArrayList<>();

        for (int i = 0; i < playingCards.size(); i++) {
            DeckCard deckCard = new DeckCard();
            deckCard.setPosition(i);
            deckCard.setCard(playingCards.get(i));
            deckCard.setGame(game);
            deckCard.setStatus("In Playing Deck");

            deckCards.add(deckCard);
        }

        deckCardRepository.saveAll(deckCards);

        List<DeckCardDto> deckCardDtos = deckCards
                .stream()
                .map(DeckCardDto::new)
                .toList();

        return new GameInitDto(game.getGame_id(), deckCardDtos);
    }

    public GameInitDto startGame(Long gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow();

        List<DeckCard> deckCards = deckCardRepository.getDeckCardsByGame(game);
        List<CardsOnBoard> cardsOnBoard = cardsOnBoardRepository.getCardsOnBoardByGame(game);

        List<DeckCardDto> cardsInDeck = deckCards.stream()
                .map(DeckCardDto::new)
                .toList();

        List<DeckCardDto> boardCards = new ArrayList<>();

        for (var boardCard : cardsOnBoard) {
            boardCards.add(boardCard.getCard().convertToCardDto());
        }

        return new GameInitDto(
                game.getGame_id(),
                cardsInDeck,
                boardCards);
    }

}