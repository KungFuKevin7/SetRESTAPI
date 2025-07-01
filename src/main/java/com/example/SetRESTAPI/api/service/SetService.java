package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.converter.CardConverter;
import com.example.SetRESTAPI.api.converter.CardsOnBoardConverter;
import com.example.SetRESTAPI.api.converter.DeckCardConverter;
import com.example.SetRESTAPI.api.converter.DeckCardDtoConverter;
import com.example.SetRESTAPI.api.dto.DeckCardDto;
import com.example.SetRESTAPI.api.dto.GameStateDto;
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
    public SetService(SetRepository setRepository) {
        this.setRepository = setRepository;
    }

    public List<Set> getAllSets(){
        return setRepository.findAll();
    }

    public Optional<Set> getSetById(Long id){
        return setRepository.findById(id);
    }

    public boolean validateAndSaveSet(List<DeckCardDto> cards, int gameId){

        boolean setValid = this.setLogic.isValidSet(cards);
        Game game = gameRepository.findById((long)gameId).orElseThrow();

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

    public void removeCardsFromBoard(Game game, List<DeckCardDto> validSetCards) {
        var cardsOnBoardGame = cardsOnBoardService.getCardsOnBoardByGame(game);

        List<DeckCardDto> cardsOnBoard = new CardsOnBoardConverter().convertList(cardsOnBoardGame);

        List<Integer> cardsRemoveFromBoard = new ArrayList<>();
        for (DeckCardDto card : cardsOnBoard){
            for (DeckCardDto validSetCard : validSetCards){
                if (card.getCardId() == validSetCard.getCardId()){
                    cardsRemoveFromBoard.add(card.getCardId());

                    //cardsOnBoardRepository.deleteCardsOnBoardByCard(card);
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


            cardsOnBoardService.replaceCardsOnBoard(validNewCards, game.getGame_id());
            return validNewCards;

        }else {
            //No Valid Combination available, Win state
            return null;
        }
    }


    public GameStateDto handleNewBoard(int gameId, List<DeckCardDto> foundSetCards) {

        if (!validateAndSaveSet(foundSetCards, gameId)){
            return new GameStateDto(gameId, new ArrayList<>());
        }

        //Get game
        Game game = gameRepository.findById((long)gameId).orElseThrow();

        //Remove Cards From Board + Mark cards as in found Set
        removeCardsFromBoard(game, foundSetCards);


        //Get Cards on Board
        List<CardsOnBoard> currentCardsOnBoard =
                cardsOnBoardRepository.getCardsOnBoardByGame(game,
                Sort.by(Sort.Direction.ASC,
                        "boardPosition"));

        // Convert cards on board to Dto
        List<DeckCardDto> currentCardsOnBoardDtos = new CardsOnBoardConverter().convertList(currentCardsOnBoard);

        //Get Cards in Deck
        List<DeckCard> deckCards = deckCardService.getDeckCardsInDeck(game);

        //Convert Cards in Deck
        List<DeckCardDto> deckCardsDto = new DeckCardConverter().convertList(deckCards);

        //Get New Cards That Form a valid table
        List<DeckCardDto> threeNewCards = getNewCardsOnBoard(game, deckCardsDto, currentCardsOnBoardDtos);

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
                    null,
                    currentCardsOnBoardDtos,
                    gameStatsDtoService.getGameStatsDto(gameId));
        }
        else {
            List<DeckCardDto> newTable = Stream.concat(currentCardsOnBoardDtos.stream(), threeNewCards.stream()).toList();
            return new GameStateDto(gameId,
                    deckCardsDto,
                    GameStatus.InProgress,
                    null,
                    newTable,
                    gameStatsDtoService.getGameStatsDto(gameId));
        }
    }

    public Set addSet(int gameId) {

        Game game = gameRepository.findById((long)gameId).orElse(null);

        Set set = new Set();
        set.setGame(game);
        return setRepository.save(set);
    }

    public List<SetCard> addSetCard(List<DeckCardDto> cards, Set set){
        var setCards = new DeckCardDtoConverter().convertSetCardList(cards, set);
        return setCardRepository.saveAll(setCards);
    }

    public List<Set> addSet(List<Set> set){
        return setRepository.saveAll(set);
    }

    public void deleteSet(Long id){
        if (setRepository.existsById(id)){
            setRepository.deleteById(id);
        } else{
            throw new RuntimeException("Set does not exist");
        }
    }
}
