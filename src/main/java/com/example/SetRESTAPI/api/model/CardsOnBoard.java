package com.example.SetRESTAPI.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CardsOnBoard")
public class CardsOnBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CARDS_ON_BOARD;

    @ManyToOne
    @JoinColumn(name="CARD_ID")
    private Card CARD;

    @ManyToOne
    @JoinColumn(name="GAME_ID")
    private Game GAME;
}
