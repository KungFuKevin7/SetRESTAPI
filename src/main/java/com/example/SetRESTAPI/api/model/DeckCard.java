package com.example.SetRESTAPI.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class DeckCard {

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private int deckCardId;

    @ManyToOne
    @Getter
    @Setter
    @JoinColumn(name="card_id")
    private Card card;

    @ManyToOne
    @Getter
    @Setter
    @JoinColumn(name="game_id")
    private Game game;

    @Getter
    @Setter
    private int position;

    @Getter
    @Setter
    private String status;
}
