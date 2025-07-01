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
import com.example.SetRESTAPI.api.repository.*;
import com.example.SetRESTAPI.logic.SetLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

        /// Set Deckcards to In Found Set
        if (setValid){
            List<DeckCard> deckCards = deckCardRepository.getDeckCardsByGame(game);

            deckCards

            Set set = addSet(gameId);
            addSetCard(cards, set);

            deckCardService.updateDeckCardStatus(
                    game,
                    cards,
                    CardStatus.inFoundSet);
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
                List<CardsOnBoard> remainingBoardCards = cardsOnBoardRepository.getCardsOnBoardByGame(
                        game, Sort.by(Sort.Direction.ASC, "boardPosition"));

                //Convert to Dto
                List<DeckCardDto> remainingBoardCardDto = new CardsOnBoardConverter().convertList(remainingBoardCards);

                //Form new cards on board
                newBoard = Stream.concat(remainingBoardCardDto.stream(), subList.stream()).toList();

                for (DeckCardDto board : newBoard){
                    board.setStatus(CardStatus.onTable);
                }
                //Convert To type card

                newBoardCards = new DeckCardDtoConverter().convertList(newBoard);
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

    public GameStateDto handleNewBoard(int gameId, List<DeckCardDto> foundSetCards) {

        if (!validateAndSaveSet(foundSetCards, gameId)){
            return new GameStateDto(gameId, new ArrayList<>());
        }

        Game game = gameRepository.findById((long)gameId).orElseThrow();
        List<DeckCard> deckCards = deckCardService.getDeckCardsInDeck(game);

        List<DeckCardDto> deckCardsDto = new DeckCardConverter().convertList(deckCards);

        removeCardsFromBoard(game, foundSetCards);
        List<DeckCardDto> newTable = getNewCardsOnBoard(game, deckCardsDto);

        return new GameStateDto(gameId,
                deckCardsDto,
                newTable,
                gameStatsDtoService.getGameStatsDto(gameId));
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
