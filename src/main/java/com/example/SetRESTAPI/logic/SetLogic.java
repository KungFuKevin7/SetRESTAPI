package com.example.SetRESTAPI.logic;

import com.example.SetRESTAPI.api.model.Card;

import java.util.ArrayList;
import java.util.List;

public class SetLogic {

    public List<Card> insertPlayingCards(){
        char[] Shape = {
            'w',
            'o',
            'd'
        };

        char[] Texture = {
            'f',
            'h',
            'e'
        };

        char[] Colour = {
            'r',
            'g',
            'b'
        };

        List<Card> playingCards = new ArrayList<Card>(90);

        //Loops galore!!!
        for (int amount = 1; amount < 4; amount++) {
            for (char texture : Texture) {
                for (char colour : Colour){
                    for (char shape : Shape) {

                        String img = shape+"_"+texture+"_"+colour+".png";
                        System.out.println(img);

                        Card c = new Card(img, amount, texture,
                                shape,
                                colour);

                        playingCards.add(c);
                    }
                }
            }
        }

        return playingCards;
    }
}
