package com.example.SetRESTAPI.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PLAYER_ID;

    @Column(unique = true, nullable = false)
    private String PLAYER_NAME;
}
