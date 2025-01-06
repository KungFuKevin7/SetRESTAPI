package com.example.SetRESTAPI.logic;

import com.example.SetRESTAPI.api.model.Card;

import java.util.ArrayList;
import java.util.List;

public class SetLogic {

    public enum Shape {
            w,  //Wave
            o,  //Oval
            d   //Diamond
    };

    public enum Texture {
            f,  //Full
            h,  //Half
            e   //Empty
    };

    private enum Colour {
            r,  //Red
            g,  //Green
            b   //Blue
    };

    public List<Card> insertPlayingCards(){

        List<Card> playingCards = new ArrayList<Card>(90);

        //Loops galore!!!
        for (int amount = 1; amount < 4; amount++) {
            for (Texture texture : Texture.values()) {
                for (Colour colour : Colour.values()){
                    for (Shape shape : Shape.values()) {

                        String img = shape+"_"+texture+"_"+colour+".png";
                        //System.out.println(img);

                        Card c = new Card(img, amount, texture.toString().charAt(0),
                                shape.toString().charAt(0),
                                colour.toString().charAt(0));


                        playingCards.add(c);
                    }
                }
            }
        }

        return playingCards;
    }


}
