package com.example.SetRESTAPI.api.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StatsDto {
    private int setsOnTable;
    private int setsFound;
    private int cardsRemaining;
}
