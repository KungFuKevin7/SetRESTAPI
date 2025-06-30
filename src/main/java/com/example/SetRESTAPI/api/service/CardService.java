package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.model.Card;
import com.example.SetRESTAPI.api.repository.CardRepository;
import com.example.SetRESTAPI.logic.SetInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;


    @Autowired
    private SetLogic setLogic;

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public List<Card> getShuffledBoardCards() {
        List<Card> cards = cardRepository.findAll();
        Collections.shuffle(cards);
        return cards;
    }

    public List<Card> getShuffledTableCards(){
        List<Card> shuffledTableCards;

        do{
            shuffledTableCards = cardRepository.getRandomTableCards();
        } while (setLogic.FindSetOnTable(shuffledTableCards.toArray(new Card[0]))
                .isEmpty());

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
        var cards = new SetInit().insertPlayingCards();
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
