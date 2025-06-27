package com.example.SetRESTAPI.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "CardsOnBoard")
public class CardsOnBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int card_on_board_id;

    @Setter
    @ManyToOne
    @JoinColumn(name="card_id")
    private Card card;

    @Setter
    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;

}
