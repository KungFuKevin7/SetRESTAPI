package com.example.SetRESTAPI.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "SetCard")
public class SetCard {

    @Id
    @Getter
    @Setter
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int SetCardId;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card Card;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "set_id")
    private Set Set;

}
