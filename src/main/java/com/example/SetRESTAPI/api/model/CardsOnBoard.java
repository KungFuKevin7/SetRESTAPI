package com.example.SetRESTAPI.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CardsOnBoard")
public class CardsOnBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int card_on_board_id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="card_id")
    private Card card;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;

    public int getCardOnboardId() {
        return this.card_on_board_id;
    }

    public void setCardOnBoardId(int cards_on_board_id) {
        this.card_on_board_id = cards_on_board_id;
    }

}
