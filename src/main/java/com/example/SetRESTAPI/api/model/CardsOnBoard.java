package com.example.SetRESTAPI.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CardsOnBoard")
public class CardsOnBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int card_on_board_id;

    @ManyToOne
    @JoinColumn(name="card_id")
    private Card card;

    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;

    public int getCardOnboardId() {
        return this.card_on_board_id;
    }

    public void setCardOnBoardId(int cards_on_board_id) {
        this.card_on_board_id = cards_on_board_id;
    }

    public Card getCard(){
        return this.card;
    }

    public void setCard(Card card){
        this.card = card;
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game){
        this.game = game;
    }
}
