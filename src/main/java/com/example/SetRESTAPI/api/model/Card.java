package com.example.SetRESTAPI.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cards")
public class Card {

    @Id
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
