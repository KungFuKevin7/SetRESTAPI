package com.example.SetRESTAPI.api.controller;

import com.example.SetRESTAPI.api.model.Card;
import com.example.SetRESTAPI.api.service.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/deck")
public class DeckController {

    @Autowired
    private DeckService deckService;

    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    @GetMapping("/full-shuffled")
    public List<Card> getShuffledDeck() {
        return deckService.getShuffledDeck();
    }
}
