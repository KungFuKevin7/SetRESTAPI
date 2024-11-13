package com.example.SetRESTAPI.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "SetsFound")
public class SetFound {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sets_found_id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Column
    private int set_number;


    public int getSetsFoundId(){
        return sets_found_id;
    }

    public void setSetsFoundId(int sets_found_id){
        this.sets_found_id = sets_found_id;
    }

    public Game getGame(){
        return game;
    }

    public void setGame(Game game){
        this.game = game;
    }

    public int getSetNumber(){
        return set_number;
    }

    public void setSetNumber(int set_number){
        this.set_number = set_number;
    }

}
