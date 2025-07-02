package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.model.Card;
import com.example.SetRESTAPI.api.repository.CardRepository;
import com.example.SetRESTAPI.logic.SetInit;
import com.example.SetRESTAPI.logic.SetLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public List<Card> getAllCards() {
        return cardRepository.findAll();
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


}
