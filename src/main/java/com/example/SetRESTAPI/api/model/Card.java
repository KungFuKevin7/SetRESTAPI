package com.example.SetRESTAPI.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Cards")
public class Card {

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

    public int getCard_id() {
        return this.card_id;
    }

    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }

    public String getCard_img() {
        return this.card_img;
    }
    public void setCard_img(String card_img) {
        this.card_img = card_img;
    }

    public int getDisplayed_amount() {
        return this.displayed_amount;
    }

    public void setDisplayed_amount(int displayed_amount) {
        this.displayed_amount = displayed_amount;
    }

    public char getTexture() {
        return this.texture;
    }

    public void setTexture(char texture) {
        this.texture = texture;
    }

    public char getShape() {
        return this.shape;
    }

    public void setShape(char shape) {
        this.shape = shape;
    }

    public char getColour() {
        return this.colour;
    }

    public void setColour(char colour) {
        this.colour = colour;
    }
}
