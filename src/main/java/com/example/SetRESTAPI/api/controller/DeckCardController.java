package com.example.SetRESTAPI.api.controller;

import com.example.SetRESTAPI.api.model.Card;
import com.example.SetRESTAPI.api.model.DeckCard;
import com.example.SetRESTAPI.api.model.Game;
import com.example.SetRESTAPI.api.service.DeckCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/deck-card")
public class DeckCardController {

    @Autowired
    DeckCardService deckCardService;

    @GetMapping("/full-shuffled")
    public List<DeckCard> getShuffledDeck(Game game) {
        return deckCardService.getDeckCards(game);
    }
}
