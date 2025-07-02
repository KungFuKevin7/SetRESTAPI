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
    private CardsOnBoardService cardsOnBoardService;
    @Autowired
    private DeckCardRepository deckCardRepository;

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
