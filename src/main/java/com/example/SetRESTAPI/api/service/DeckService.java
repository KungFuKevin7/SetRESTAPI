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

    public DeckService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> getShuffledDeck() {
        List<Card> cards = cardRepository.findAll();
        Collections.shuffle(cards);
        return cards;
    }
}
