package com.example.SetRESTAPI.api.service;
import com.example.SetRESTAPI.api.converter.SetCardConverter;
import com.example.SetRESTAPI.api.dto.DeckCardDto;
import com.example.SetRESTAPI.api.dto.SetDto;
import com.example.SetRESTAPI.api.model.Game;
import com.example.SetRESTAPI.api.model.Set;
import com.example.SetRESTAPI.api.model.SetCard;
import com.example.SetRESTAPI.api.repository.GameRepository;
import com.example.SetRESTAPI.api.repository.SetCardRepository;
import com.example.SetRESTAPI.api.repository.SetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class FoundSetService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private SetRepository setRepository;

    @Autowired
    private SetCardRepository setCardRepository;


    public List<SetDto> getFoundSets(int gameId) {

        Game game = gameRepository.findById((long)gameId)
                .orElseThrow();
        //Get Sets
        var sets = setRepository.findByGame(game);

        List<SetDto> setCardsFromGame = new ArrayList<>();

        for (Set set : sets) {

            SetDto setDto = new SetDto();
            setDto.setSetId(set.getSetId());
            setDto.setSetNumber(set.getSetNumber());

            //Get cards by Set
            List<SetCard> setCards = setCardRepository.findAllBySetId(set.getSetId());

            List<DeckCardDto> cards = new SetCardConverter().convertList(setCards);
            setDto.setCards(cards);

            setCardsFromGame.add(setDto);
        }

        return setCardsFromGame;
    }
}
