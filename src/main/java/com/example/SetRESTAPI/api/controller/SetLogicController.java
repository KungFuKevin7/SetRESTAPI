package com.example.SetRESTAPI.api.controller;

import com.example.SetRESTAPI.api.model.Card;
import com.example.SetRESTAPI.logic.SetLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Arrays;

@RestController
@RequestMapping("/api/check-set")
@CrossOrigin
public class SetLogicController {

    @Autowired
    private final SetLogic setLogic;

    public SetLogicController(SetLogic setLogicService) {
        this.setLogic = setLogicService;
    }

    //POST, since GET request oughtn't have a body
    @PostMapping()
    public boolean CheckSetValidity(@RequestBody Card[] cards) {
        return setLogic.checkIfSet(Arrays.stream(cards).toList());
    }

    @PostMapping("/on-table")
    public List<List<Card>> CheckIfSetOnTable(@RequestBody Card[] cardsOnTable) {
        return setLogic.FindSetOnTable(cardsOnTable);
    }

    @PostMapping("/hint")
    public List<Card> CheckIfSetHint(@RequestBody Card[] cardsOnTable) {
        return setLogic.getSetHint(cardsOnTable);
    }

}
