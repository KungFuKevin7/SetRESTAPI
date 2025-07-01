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

    public void updateDeckCardStatus(Game game, List<DeckCardDto> cards, String Status){
        List<DeckCard> deckCards = getDeckCards(game);

        for (DeckCard deckCard : deckCards) {
            for(DeckCardDto card : cards) {
                if (deckCard.getCard().getCard_id() == card.getCardId()){

                    DeckCard itemToUpdate = deckCardRepository.findById((long)deckCard.getDeckCardId())
                            .orElseThrow();

                    itemToUpdate.setStatus(Status);
                    deckCardRepository.save(itemToUpdate);
                }
            }
        }
    }

    public List<DeckCard> getDeckCardsInDeck(Game game){
        return deckCardRepository.getDeckCardsInDeckByGame(game.getGame_id());
    }
}
