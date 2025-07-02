package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.dto.DeckCardDto;
import com.example.SetRESTAPI.api.model.Card;
import com.example.SetRESTAPI.api.model.DeckCard;
import com.example.SetRESTAPI.api.model.Game;
import com.example.SetRESTAPI.api.repository.DeckCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeckCardService {

    private final DeckCardRepository deckCardRepository;

    public DeckCardService(DeckCardRepository deckCardRepository) {
        this.deckCardRepository = deckCardRepository;
    }

    public List<DeckCard> getDeckCards(Game game)
    {
        return deckCardRepository.getDeckCardsByGame(game);
    }

    public List<DeckCard> getDeckCardsInDeck(Game game){
        return deckCardRepository.getDeckCardsInDeckByGame(game.getGame_id());
    }
}
