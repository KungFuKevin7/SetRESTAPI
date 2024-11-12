package com.example.SetRESTAPI.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Players")
public class Player {

    @Id
    private int PLAYER_ID;

    @Column
    private String PLAYER_NAME;
}
