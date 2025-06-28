package com.example.SetRESTAPI.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Set")
public class Set {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int SetId;


    @ManyToOne
    @Getter
    @Setter
    @JoinColumn(name="game_id")
    private Game game;

}
