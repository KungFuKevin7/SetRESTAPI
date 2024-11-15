package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.model.Card;
import com.example.SetRESTAPI.api.repository.CardRepository;
import com.example.SetRESTAPI.logic.SetLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> getAllCards(){
        return cardRepository.findAll();
    }

    public List<Card> getShuffledTableCards(){
        return cardRepository.getRandomTableCards();
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
