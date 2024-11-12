package com.example.SetRESTAPI.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CARD_ID;

    @Column
    private String CARD_IMG;

    @Column
    private int DISPLAYED_AMOUNT;

    @Column
    private int TEXTURE_ID;

    @Column
    private int SHAPE_ID;

    @Column
    private int COLOUR_ID;

}
