package com.example.SetRESTAPI.api.repository;

import com.example.SetRESTAPI.api.controller.CardController;
import com.example.SetRESTAPI.api.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
/*
    public CardRepository() {

    }

    public void InsertCard(Card card) {

    }

    public void InsertAllCards() {

        CardController cardController = new CardController();

        int[] shapes = {1,2,3};
        int[] textures = {1,2,3};
        int[] amounts = {1,2,3};
        int[] colours= {1,2,3};

        for (int shape : shapes) {
            for (int texture : textures) {
                for (int colour : colours){
                    for (int amount : amounts) {


                    }
                }
            }
        }
    }*/
}
