package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SetLogicService {

    ///Find out whether the current playing table contains at least one set
    public List<List<Card>> FindSetOnTable(Card[] cardsOnTable){
        List<List<Card>> validSets = CheckIfSetsOnTable(Arrays.stream(cardsOnTable).toList(),
                new ArrayList<>(), 0);

        for (int i = 0; i < validSets.size(); i++) {
            System.out.println("Set "+ i);
            for (Card card : validSets.get(i)) {
                System.out.println(
                        card.getDisplayedAmount() + ", " +
                        card.getShape() + ", " +
                        card.getTexture() + ", " +
                        card.getColour());
            }
        }
        return validSets;
    }

    /// Check all card combinations from the table
    public List<List<Card>> CheckIfSetsOnTable(List<Card> cardsOnTable, List<Card> currentCheckingSet, int index) {
        List<List<Card>> sets = new ArrayList<List<Card>>();

        if (currentCheckingSet.size() == 3){
            //check if current set is a valid set
            if (checkIfSet(currentCheckingSet)){
                sets.add(new ArrayList<Card>(currentCheckingSet));
            }
            return sets;
        }

        for (int i = index; i < cardsOnTable.size(); i++) {
            currentCheckingSet.add(cardsOnTable.get(i));
            sets.addAll(CheckIfSetsOnTable(cardsOnTable, currentCheckingSet, i + 1));
            currentCheckingSet.removeLast();
        }

        return sets;
    }

    ///Check if three cards make up a correct set
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
                isCardPropertyValid((char)(cardsToCheck.get(0).getDisplayedAmount()+'0'),
                        (char)(cardsToCheck.get(1).getDisplayedAmount()+'0'),
                        (char)(cardsToCheck.get(2).getDisplayedAmount()+'0'));
    }

    /// Check if a property of a set of cards is valid
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
    }
}
