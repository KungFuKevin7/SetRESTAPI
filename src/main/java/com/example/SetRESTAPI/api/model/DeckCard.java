package com.example.SetRESTAPI.api.model;

import jakarta.persistence.*;

@Entity
public class DeckCard {

    @Id
    @GeneratedValue
    private int deckCardId;

    @ManyToOne
    @JoinColumn(name="card_id")
    private Card card;

    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;

    private int position;

    private String status;
}
