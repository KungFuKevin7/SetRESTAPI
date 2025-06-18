package com.example.SetRESTAPI.api.model;

import jakarta.persistence.*;
import org.apache.catalina.User;

@Entity
@Table(name = "Games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int game_id;

    @Column
    private int sets_found;

    @Column
    private int elapsed_time;

    @ManyToOne
    @JoinColumn(name = "UserId")
    private Users users;


    public int getGameId() {
        return game_id;
    }

    public void setGameId(int game_id){
        this.game_id = game_id;
    }

    public int getSetsFound(){
        return sets_found;
    }

    public void setSetsFound(int sets_found){
        this.sets_found = sets_found;
    }

    public int getElapsedTime(){
        return elapsed_time;
    }

    public void setElapsedTime(int time){
        this.elapsed_time = time;
    }

    public Users getUser(){
        return users;
    }

    public void setUsers(Users user){
        this.users = user;
    }
    public void setUsersById(int userId){
        this.users.setUserid(userId);
    }

}
