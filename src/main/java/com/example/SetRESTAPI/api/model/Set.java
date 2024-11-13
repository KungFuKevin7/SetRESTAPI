package com.example.SetRESTAPI.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Set")
public class Set {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int set_id;

    @ManyToOne
    @JoinColumn(name="card_id")
    private Card card;

    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;


    public int getSetId() {
        return set_id;
    }

    public void setSetId(int set_id){
        this.set_id = set_id;
    }

    public Card getCard(){
        return card;
    }

    public void setCard(Card card){
        this.card = card;
    }

    public Game getGame(){
        return game;
    }

    public void setGame(Game game){
        this.game = game;
    }

}
