package com.example.SetRESTAPI.api.dto;

import com.example.SetRESTAPI.api.model.DeckCard;
import com.example.SetRESTAPI.api.model.Card;
import lombok.Getter;
import lombok.Setter;

public class DeckCardDto {

    @Getter
    @Setter
    private int cardId;

    @Getter
    @Setter
    private int position;

    @Getter
    @Setter
    private String status;

    @Getter
    @Setter
    private char texture;

    @Getter
    @Setter
    private char shape;

    @Getter
    @Setter
    private char colour;

    @Getter
    @Setter
    private int displayed_amount;

    @Getter
    @Setter
    private int gameId;

    public DeckCardDto(DeckCard deckCard) {
        Card card = deckCard.getCard();
        this.cardId = card.getCardId();
        this.position = deckCard.getPosition();
        this.status = deckCard.getStatus();
        this.shape = card.getShape();
        this.colour = card.getColour();
        this.texture = card.getTexture();
        this.displayed_amount = card.getDisplayedAmount();
        this.gameId = deckCard.getGame().getGame_id();
    }



}
