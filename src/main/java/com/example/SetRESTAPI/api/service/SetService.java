package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.converter.*;
import com.example.SetRESTAPI.api.dto.DeckCardDto;
import com.example.SetRESTAPI.api.dto.GameStateDto;
import com.example.SetRESTAPI.api.dto.SetDto;
import com.example.SetRESTAPI.api.dto.SetResponseDto;
import com.example.SetRESTAPI.api.model.*;
import com.example.SetRESTAPI.api.publics.CardStatus;
import com.example.SetRESTAPI.api.publics.GameStatus;
import com.example.SetRESTAPI.api.repository.*;
import com.example.SetRESTAPI.logic.SetLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SetService
{
    @Autowired
    private SetRepository setRepository;
    @Autowired
    private SetCardRepository setCardRepository;
    @Autowired
    private SetLogic setLogic;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private DeckCardService deckCardService;
    @Autowired
    private CardsOnBoardService cardsOnBoardService;
    @Autowired
    private CardsOnBoardRepository cardsOnBoardRepository;
    @Autowired
    private GameStatsDtoService gameStatsDtoService;
    @Autowired
    private DeckCardRepository deckCardRepository;
    @Autowired
    private FoundSetService foundSetService;
    @Autowired
    private BoardService boardService;

    public List<Set> getAllSets(){
        return setRepository.findAll();
    }

    public Optional<Set> getSetById(Long id){
        return setRepository.findById(id);
    }

    public boolean validateAndSaveSet(List<DeckCardDto> cards, int gameId){

        boolean setValid = this.setLogic.isValidSet(cards);

        List<Integer> cardIds = new ArrayList<>();

        /// Set Deckcards to In Found Set
        if (setValid){

            for (DeckCardDto card: cards){
                cardIds.add(card.getCardId());
            }

            deckCardRepository.setDeckCardStatusToFoundSet(cardIds.toArray(new Integer[0]), gameId);

            Set set = addSet(gameId);
            addSetCard(cards, set);
        }

        return setValid;
    }

    /*public void removeCardsFromBoard(Game game, List<DeckCardDto> validSetCards) {
        var cardsOnBoard = cardsOnBoardService.getCurrentCardsOnBoard(game.getGame_id());

        List<Integer> cardsRemoveFromBoard = new ArrayList<>();
        for (DeckCardDto card : cardsOnBoard){
            for (DeckCardDto validSetCard : validSetCards){
                if (card.getCardId() == validSetCard.getCardId()){
                    cardsRemoveFromBoard.add(card.getCardId());
                }
            }
        }

        cardsOnBoardRepository.deleteCardsOnBoardByCard(
                cardsRemoveFromBoard.toArray(new Integer[0]),
                game.getGame_id());
    }

    public List<DeckCardDto> getNewCardsOnBoard(Game game, List<DeckCardDto> cardsInDeck, List<DeckCardDto> currentCardsOnBoard) {

        List<DeckCardDto> validNewCards = null;

        List<List<DeckCardDto>> possibleBoards = setLogic.generatePossibilities(cardsInDeck, 3);
        for (List<DeckCardDto> possibleBoard : possibleBoards){
            List<DeckCardDto> board = new ArrayList<>(currentCardsOnBoard);
            board.addAll(possibleBoard);

            board.forEach(card -> card.setStatus(CardStatus.onTable));

            List<Card> testCards = new DeckCardDtoConverter().convertList(board);

            if (!setLogic.FindSetOnTable(testCards.toArray(new Card[0])).isEmpty()){
                validNewCards = possibleBoard;
            }
        }
        if (validNewCards != null){
            cardsOnBoardService.addCardsOnBoard(validNewCards, game.getGame_id());
            return validNewCards;

        } else {
            endGame(game.getGame_id());
            //No Valid Combination available, Win state
            return new ArrayList<>();
        }
    }*/

/*
    public GameStateDto handleNewBoard(int gameId, List<DeckCardDto> foundSetCards) {

        //Get game
        Game game = gameRepository.findById((long)gameId).orElseThrow();

        //Get Cards in Deck
        List<DeckCard> deckCards = deckCardService.getDeckCardsInDeck(game);
        List<DeckCardDto> deckCardsDto = new DeckCardConverter().convertList(deckCards);

        //Validate Set
        if (!validateAndSaveSet(foundSetCards, gameId)){
            return new GameStateDto(gameId,
                    deckCardsDto,
                    GameStatus.InProgress,
                    getFoundSets(gameId),
                    cardsOnBoardService.getCurrentCardsOnBoard(gameId),
                    gameStatsDtoService.getGameStatsDto(gameId));
        }

        //Remove Cards From Board + Mark cards as in found Set
        removeCardsFromBoard(game, foundSetCards);

        // Get
        List<DeckCardDto> currentCardsOnBoard =
                cardsOnBoardService.getCurrentCardsOnBoard(gameId);

        //Get Cards in Deck
        deckCards = deckCardService.getDeckCardsInDeck(game);

        //Convert Cards in Deck
        deckCardsDto = new DeckCardConverter().convertList(deckCards);

        //Get New Cards That Form a valid table
        List<DeckCardDto> threeNewCards = getNewCardsOnBoard(game, deckCardsDto, currentCardsOnBoard);

        //Update the CardStatuses of Cards on Table
        List<Integer> cardIds = new ArrayList<>();
        threeNewCards.forEach(id -> cardIds.add(id.getCardId()));
        deckCardRepository.setDeckCardStatusToOnTable(cardIds.toArray(new Integer[0]), gameId);

        //Now get the updated deckCards
        deckCards = deckCardService.getDeckCardsInDeck(game);
        deckCardsDto = new DeckCardConverter().convertList(deckCards);

        if (threeNewCards.isEmpty()){
            //Trigger Win state
            return new GameStateDto(gameId,
                    deckCardsDto,
                    GameStatus.Completed,
                    getFoundSets(gameId),
                    currentCardsOnBoard,
                    gameStatsDtoService.getGameStatsDto(gameId));
        }
        else {
            List<DeckCardDto> newTable = Stream.concat(currentCardsOnBoard.stream(), threeNewCards.stream()).toList();
            return new GameStateDto(gameId,
                    deckCardsDto,
                    GameStatus.InProgress,
                    getFoundSets(gameId),
                    newTable,
                    gameStatsDtoService.getGameStatsDto(gameId));
        }
    }*/

    public Set addSet(int gameId) {
        Game game = gameRepository.findById((long)gameId).orElse(null);

        long setNumber = setRepository.countByGame(game);

        Set set = new Set();
        set.setGame(game);
        set.setSetNumber((int)setNumber + 1);
        return setRepository.save(set);
    }

    public List<SetCard> addSetCard(List<DeckCardDto> cards, Set set){
        var setCards = new DeckCardDtoConverter().convertSetCardList(cards, set);
        return setCardRepository.saveAll(setCards);
    }

    public List<SetDto> getFoundSets(int gameId) {
        Game game = gameRepository.findById((long)gameId).orElse(null);

        //Get Sets
        var sets = setRepository.findByGame(game);

        List<SetDto> setCardsFromGame = new ArrayList<>();

        for (Set set : sets) {

            SetDto setDto = new SetDto();
            setDto.setSetId(set.getSetId());
            setDto.setSetNumber(set.getSetNumber());

            //Get cards by Set
            List<SetCard> setCards = setCardRepository.findAllBySetId(set.getSetId());

            List<DeckCardDto> cards = new SetCardConverter().convertList(setCards);
            setDto.setCards(cards);

            setCardsFromGame.add(setDto);
        }

        return setCardsFromGame;
    }


    public void endGame(int gameId){
        gameRepository.updateGameStatusByGameId
             (GameStatus.Completed, gameId);
    }

    public void processValidSet(Game game, List<DeckCardDto> setCards){

        List<Integer> setCardIds = new ArrayList<>();
        for (DeckCardDto card: setCards){
            setCardIds.add(card.getCardId());
        }

        //Remove from Board
        cardsOnBoardService.deleteCardsOnBoard(game, setCardIds);

        //In DeckCards Set cards to Found Set
        deckCardRepository.setDeckCardStatusToFoundSet(
                setCardIds.toArray(new Integer[0]), game.getGame_id());

        //Add new Set + SetCards to Database
        Set set =
                addSet(game.getGame_id());
        addSetCard(setCards, set);
    }
}
