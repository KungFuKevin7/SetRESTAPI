package com.example.SetRESTAPI.api.model;

import jakarta.persistence.*;
import org.springframework.context.annotation.Primary;

@Entity
@Table(name = "Games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int GAME_ID;

    @Column
    private int SETS_FOUND;

//    @Column
//    private int PLAYER_ID;

    @ManyToOne
    @JoinColumn(name = "PLAYER_ID")
    private Player PLAYER;

}
