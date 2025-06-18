package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.model.Card;
import com.example.SetRESTAPI.api.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DeckService{

    private final CardRepository cardRepository;
    private final SetLogicService setLogicService;

    public DeckService(CardRepository cardRepository, SetLogicService setLogicService) {
        this.cardRepository = cardRepository;
        this.setLogicService = setLogicService;
    }

    public List<Card> getShuffledDeck() {
        List<Card> cards = cardRepository.findAll();
        Collections.shuffle(cards);
        return cards;
    }
}
