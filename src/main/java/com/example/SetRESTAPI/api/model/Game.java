package com.example.SetRESTAPI.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Games")
public class Game {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int game_id;

    @Column
    @Getter
    @Setter
    private int sets_found;

    @Column
    @Getter
    @Setter
    private int elapsed_time;

    @ManyToOne
    @JoinColumn(name = "UserId")
    @Getter
    @Setter
    private Users users;

    @Column
    @Getter
    @Setter
    private String status;

    @Column
    @Getter
    @Setter
    private LocalDateTime created_at;
}
