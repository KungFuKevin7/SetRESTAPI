package com.example.SetRESTAPI.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int player_id;

    @Column(unique = true, nullable = false)
    private String player_name;

    public int getPlayerId() {
        return player_id;
    }

    public void setPlayerId(int player_id){
        this.player_id = player_id;
    }

    public String getPlayerName(){
        return player_name;
    }

    public void setPlayerName(String player_name){
        this.player_name = player_name;
    }
}
