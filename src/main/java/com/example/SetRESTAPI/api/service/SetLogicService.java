package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SetLogicService {

    public List<List<Card>> FindSetOnTable(List<Card> cardsOnTable){
        List<Card> TableCards = new ArrayList<Card>();
        TableCards.add(new Card("AA", 3,'F', 'W','G'));
        TableCards.add(new Card("AA", 2,'F','W', 'R'));
        TableCards.add(new Card("AA", 1,'E','O', 'B'));
        TableCards.add(new Card("AA", 2, 'F', 'D','G'));
        TableCards.add(new Card("AA", 1, 'F', 'D','G'));
        TableCards.add(new Card("AA", 2,'H', 'D', 'R'));
        TableCards.add(new Card("AA", 3,'F','W', 'R'));
        TableCards.add(new Card("AA", 1, 'H','O', 'B'));
        TableCards.add(new Card("AA", 3,'F','W', 'G'));
        TableCards.add(new Card("AA", 1,'E','D','G'));
        TableCards.add(new Card("AA", 2,'H','O', 'G'));
        TableCards.add(new Card("AA", 2,'F','W', 'G'));
        return CheckIfSetsOnTable(TableCards, new ArrayList<Card>(), 0);
    }

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
