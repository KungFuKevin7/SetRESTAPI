package com.example.SetRESTAPI.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int player_id;

    @Setter
    @Getter
    @Column(unique = true, nullable = false)
    private String player_name;

    @Setter
    @Getter
    @Column(nullable = false)
    private String password;

    @CreatedDate
    @Column(name = "created_at")
    private Date created_At;


    //Getters and Setters
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
