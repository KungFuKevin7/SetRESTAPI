package com.example.SetRESTAPI.api.controller;

import com.example.SetRESTAPI.api.model.Card;
import com.example.SetRESTAPI.api.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cards")
@CrossOrigin
public class CardController{

    @Autowired
    private CardService cardService;

    @GetMapping
    public List<Card> getAllCards(){
        return cardService.getAllCards();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable Long id){
        Optional<Card> card = cardService.getCardById(id);
        return card.map(ResponseEntity::ok).orElseGet(() ->
            ResponseEntity.notFound().build()
        );
    }

    @PostMapping
    public Card createCard(@RequestBody Card card){
        return cardService.addCard(card);
    }

    @PostMapping("/add-all")
    public List<Card> addAllCards(){
        return cardService.addPlayingCards();
    }

}
