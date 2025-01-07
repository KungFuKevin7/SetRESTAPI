package com.example.SetRESTAPI.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table(name = "Player")
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




}
