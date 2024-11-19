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


/*
    public boolean checkIfSet(List<Card> cardsToCheck){

        //Check for each property if they are all equal or varying
        return isCardPropertyValid(cardsToCheck.get(0).getShape(),
                cardsToCheck.get(1).getShape(),
                cardsToCheck.get(2).getShape())
                &&
                isCardPropertyValid(cardsToCheck.get(0).getTexture(),
                        cardsToCheck.get(1).getTexture(),
                        cardsToCheck.get(2).getTexture())
                &&
                isCardPropertyValid(cardsToCheck.get(0).getColour(),
                        cardsToCheck.get(1).getColour(),
                        cardsToCheck.get(2).getColour())
                &&
                isCardPropertyValid((char)cardsToCheck.get(0).getDisplayedAmount(),
                        (char)cardsToCheck.get(1).getDisplayedAmount(),
                        (char)cardsToCheck.get(2).getDisplayedAmount());
    }

    public boolean isCardPropertyValid(char firstCardProperty,
                                       char secondCardProperty,
                                       char thirdCardProperty){

        if ((firstCardProperty == secondCardProperty) &&
                (firstCardProperty == thirdCardProperty)) {
            //In case properties all match
            return true;
        }
        if ((firstCardProperty != secondCardProperty) &&
                (secondCardProperty != thirdCardProperty) &&
                (thirdCardProperty != firstCardProperty)) {
            //In case properties are varying
            return true;
        }
        return false;
    }*/

}
