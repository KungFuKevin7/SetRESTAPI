package com.example.SetRESTAPI.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "Games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int game_id;

    @ManyToOne
    @JoinColumn(name = "UserId")
    private Users users;

    @Column
    private String status;

    @Column
    private LocalDateTime created_at;
}
