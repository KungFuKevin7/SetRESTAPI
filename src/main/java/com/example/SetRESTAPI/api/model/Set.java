package com.example.SetRESTAPI.api.model;

import jakarta.persistence.*;

@Entity
public class Set {

    @Id
    private int SET_ID;

    @ManyToOne
    @JoinColumn(name="CARD_ID")
    private Card card;

    @ManyToOne
    @JoinColumn(name="GAME_ID")
    private Game game;

}
