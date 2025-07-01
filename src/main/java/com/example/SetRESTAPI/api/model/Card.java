package com.example.SetRESTAPI.api.model;

import com.example.SetRESTAPI.api.dto.DeckCardDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Cards")
public class Card {

    public Card(){}
    public Card(String card_img, int displayed_amount, char texture, char shape, char colour){
        this.card_img = card_img;
        this.displayed_amount = displayed_amount;
        this.texture = texture;
        this.shape = shape;
        this.colour = colour;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int card_id;

    @Column
    private String card_img;

    @Column
    private int displayed_amount;

    @Column
    private char texture;

    @Column
    private char shape;

    @Column
    private char colour;


}
