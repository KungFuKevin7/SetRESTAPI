package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.dto.DeckCardDto;
import com.example.SetRESTAPI.api.model.Set;
import com.example.SetRESTAPI.logic.SetLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SetValidatorService {

    @Autowired
    private SetLogic setLogic;

    public boolean isValidSet(List<DeckCardDto> proposedSet){
        return setLogic.isValidSet(proposedSet);
    }
}
