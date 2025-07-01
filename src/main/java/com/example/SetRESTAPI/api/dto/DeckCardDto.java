package com.example.SetRESTAPI.api.dto;

import com.example.SetRESTAPI.api.model.DeckCard;
import com.example.SetRESTAPI.api.model.Card;
import com.example.SetRESTAPI.api.model.Game;
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
    private String card_img;

    public DeckCardDto(DeckCard deckCard) {
        Card card = deckCard.getCard();
        this.cardId = card.getCard_id();
        this.position = deckCard.getPosition();
        this.status = deckCard.getStatus();
        this.shape = card.getShape();
        this.colour = card.getColour();
        this.texture = card.getTexture();
        this.displayed_amount = card.getDisplayed_amount();
        this.card_img = card.getCard_img();
        //this.game = deckCard.getGame();
    }
    public DeckCardDto(int cardId, int position, String status, char shape, char colour, int displayed_amount, char texture, String card_img) {
        this.cardId = cardId;
        this.position = position;
        this.status = status;
        this.shape = shape;
        this.texture = texture;
        this.colour = colour;
        this.displayed_amount = displayed_amount;
        this.card_img = card_img;
        //this.game = deckCard.getGame();
    }
/*
    public Card convertToCard() {
        Card card = new Card();
        card.setCard_id(this.cardId);
        card.setDisplayed_amount(this.displayed_amount);
        card.setShape(this.shape);
        card.setTexture(this.texture);
        card.setColour(this.colour);
        card.setCard_img(this.card_img);

        return card;
    }*/

}
