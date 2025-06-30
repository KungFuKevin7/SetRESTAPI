package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.model.Card;
import com.example.SetRESTAPI.api.repository.CardRepository;
import com.example.SetRESTAPI.logic.SetLogic;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DeckService{

    private final CardRepository cardRepository;
    private final SetLogic setLogic;

    public DeckService(CardRepository cardRepository, SetLogic setLogic) {
        this.cardRepository = cardRepository;
        this.setLogic = setLogic;
    }

    public List<Card> getShuffledDeck() {
        List<Card> cards = cardRepository.findAll();
        Collections.shuffle(cards);
        return cards;
    }
}
