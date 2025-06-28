package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.dto.DeckCardDto;
import com.example.SetRESTAPI.api.dto.SetResponseDto;
import com.example.SetRESTAPI.api.model.Card;
import com.example.SetRESTAPI.api.model.Game;
import com.example.SetRESTAPI.api.model.Set;
import com.example.SetRESTAPI.api.model.SetCard;
import com.example.SetRESTAPI.api.repository.CardRepository;
import com.example.SetRESTAPI.api.repository.GameRepository;
import com.example.SetRESTAPI.api.repository.SetCardRepository;
import com.example.SetRESTAPI.api.repository.SetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        if (setValidity.isSetValid()){

            Set set = addSet(gameId);
            addSetCard(cards, set);
        }

        return setValidity;
    }

    public Set addSet( int gameId) {

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
