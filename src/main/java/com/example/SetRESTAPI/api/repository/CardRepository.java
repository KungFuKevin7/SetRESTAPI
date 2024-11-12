package com.example.SetRESTAPI.api.repository;

import com.example.SetRESTAPI.api.model.Card;

public class CardRepository {

    public CardRepository() {

    }

    public void InsertCard(Card card) {

    }

    public void InsertAllCards() {

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
    }
}
