package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.model.Card;
import com.example.SetRESTAPI.api.repository.CardRepository;
import com.example.SetRESTAPI.logic.SetLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final SetLogicService setLogicService;

    @Autowired
    public CardService(CardRepository cardRepository, SetLogicService setLogicService) {
        this.cardRepository = cardRepository;
        this.setLogicService = setLogicService;
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public List<Card> getAllCardsShuffled() {
        List<Card> cards = cardRepository.findAll();
        Collections.shuffle(cards);
        return cards;
    }

    public List<Card> getShuffledTableCards(){
        List<Card> shuffledTableCards;

        do{
            shuffledTableCards = cardRepository.getRandomTableCards();
        } while (setLogicService.FindSetOnTable(shuffledTableCards.toArray(new Card[0])).isEmpty());

        ///DB Call to add cards to db
        return shuffledTableCards;
    }

    public Optional<Card> getCardById(Long id){
        return cardRepository.findById(id);
    }

    public Card addCard(Card card){
        return cardRepository.save(card);
    }

    public List<Card> addPlayingCards() {
        var cards = new SetLogic().insertPlayingCards();
        return cardRepository.saveAll(cards);
    }

    public void deleteCard(Long id){
        if (cardRepository.existsById(id)){
            cardRepository.deleteById(id);
        } else{
            throw new RuntimeException("Card does not exist");
        }
    }
}
