package com.example.SetRESTAPI.api.controller;

import com.example.SetRESTAPI.api.model.Card;
import com.example.SetRESTAPI.api.service.SetLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Arrays;

@RestController
@RequestMapping("/api/check-set")
@CrossOrigin
public class SetLogicController {

    @Autowired
    private final SetLogicService setLogicService;

    public SetLogicController(SetLogicService setLogicService) {
        this.setLogicService = setLogicService;
    }

    //POST, since GET request oughtn't have a body
    @PostMapping()
    public boolean CheckSetValidity(@RequestBody Card[] cards) {
        return setLogicService.checkIfSet(Arrays.stream(cards).toList());
    }

    @PostMapping("/on-table")
    public List<List<Card>> CheckIfSetOnTable(@RequestBody Card[] cardsOnTable) {
        return setLogicService.FindSetOnTable(cardsOnTable);
    }

}
