package com.example.SetRESTAPI.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "SetsFound")
public class SetFound {

    @Id
    @ManyToOne
    @JoinColumn(name = "GAME_ID")
    private Game Game;

    @Column
    private int SET_NUMBER;

}
