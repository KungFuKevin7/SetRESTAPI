package com.example.SetRESTAPI.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "SET")
public class Set {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int SET_ID;

    @ManyToOne
    @JoinColumn(name="CARD_ID")
    private Card card;

    @ManyToOne
    @JoinColumn(name="GAME_ID")
    private Game game;

}
