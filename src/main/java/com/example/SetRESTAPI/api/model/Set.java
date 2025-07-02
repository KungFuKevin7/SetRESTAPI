package com.example.SetRESTAPI.api.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "Set")
public class Set {

    @Id
    @Getter
    @Setter
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int SetId;

    @Getter
    @Setter
    @Column
    @ColumnDefault("0")
    private int setNumber;

    @ManyToOne
    @Getter
    @Setter
    @JoinColumn(name="game_id")
    private Game game;

}
