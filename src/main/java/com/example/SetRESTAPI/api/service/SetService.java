package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.dto.DeckCardDto;
import com.example.SetRESTAPI.api.dto.GameInitDto;
import com.example.SetRESTAPI.api.dto.SetResponseDto;
import com.example.SetRESTAPI.api.model.*;
import com.example.SetRESTAPI.api.publics.CardStatus;
import com.example.SetRESTAPI.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

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
    public SetService(SetRepository setRepository) {
        this.setRepository = setRepository;
    }

    public List<Set> getAllSets(){
        return setRepository.findAll();
    }

    public Optional<Set> getSetById(Long id){
        return setRepository.findById(id);
    }

    public SetResponseDto validateAndSaveSet(List<DeckCardDto> cards, int gameId){

        SetResponseDto setValidity = this.setLogic.isValidSet(cards);

        /// Set Deckcards to In Found Set
        if (setValidity.isSetValid()){

            Set set = addSet(gameId);
            addSetCard(cards, set);

 /*           for (DeckCardDto card : cards){

            }*/

            deckCardService.updateDeckCardStatus(
                    gameRepository.findById((long)gameId).orElseThrow(),
                    cards,
                    CardStatus.inFoundSet);
        }

        return setValidity;
    }

    public void removeCardsFromBoard(Game game, List<DeckCardDto> validSetCards) {
        var cardsOnBoardGame = cardsOnBoardService.getCardsOnBoardByGame(game);
        List<DeckCardDto> cardsOnBoard = new ArrayList<>();


        for (CardsOnBoard card : cardsOnBoardGame){
            cardsOnBoard.add(card.getCard().convertToCardDto());
        }

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

    public List<DeckCardDto> getNewCardsOnBoard(Game game, List<DeckCardDto> cardsInDeck) {

        List<Card> newBoardCards = new ArrayList<>();

        List<DeckCardDto> newBoard = new ArrayList<>();
        int index = 0;
        //
        do {
            if (index + 3 <= cardsInDeck.size()) {

                //Slice 3 cards from the deck
                List<DeckCardDto> subList = cardsInDeck.subList(index, index + 3);

                //Get Remaining 9 cards in deck
                List<CardsOnBoard> remainingBoardCards = cardsOnBoardRepository.getCardsOnBoardByGame(game);

                //Convert to Dto
                List<DeckCardDto> remainingBoardCard = new ArrayList<>();
                for (CardsOnBoard card : remainingBoardCards) {
                    remainingBoardCard.add(card.getCard().convertToCardDto());
                }

                //Form new cards on board
                newBoard = Stream.concat(remainingBoardCard.stream(), subList.stream()).toList();

                for (DeckCardDto board : newBoard){
                    board.setStatus(CardStatus.onTable);
                }
                //Convert To type card
                for (DeckCardDto card : newBoard) {
                    newBoardCards.add(card.convertToCard());
                }
            }
            else{
                break;
            }
        }
        //Keep checking until we found a set
        while(setLogic.FindSetOnTable(newBoardCards.toArray(new Card[0])).isEmpty());

        cardsOnBoardService.replaceCardsOnBoard(newBoard, game.getGame_id());

        return newBoard;
    }

    public GameInitDto handleNewBoard(int gameId, List<DeckCardDto> foundSetCards) {

        validateAndSaveSet(foundSetCards, gameId);

        Game game = gameRepository.findById((long)gameId).orElseThrow();
        List<DeckCard> deckCards = deckCardService.getDeckCardsInDeckId(game);

        List<DeckCardDto> deckCardDtos = new ArrayList<>();
        for (DeckCard deckCard : deckCards){
            deckCardDtos.add(deckCard.getCard().convertToCardDto());
        }

        removeCardsFromBoard(game, foundSetCards);
        List<DeckCardDto> newTable = getNewCardsOnBoard(game, deckCardDtos);

        return new GameInitDto(game.getGame_id(), deckCardDtos, newTable);
    }

    public Set addSet(int gameId) {

        Game game = gameRepository.findById((long)gameId).orElse(null);

        Set set = new Set();
        set.setGame(game);
        return setRepository.save(set);
    }

    public List<SetCard> addSetCard(List<DeckCardDto> cards, Set set){
        List<SetCard> setCards = new ArrayList<>();
        for (DeckCardDto card : cards){
            SetCard setCard = new SetCard();
            setCard.setCard(card.convertToCard());
            setCard.setSet(set);
            setCards.add(setCard);
        }
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
