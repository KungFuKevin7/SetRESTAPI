package com.example.SetRESTAPI.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Cards_On_Board")
public class CardsOnBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int card_on_board_id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="card_id")
    private Card card;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;

}
