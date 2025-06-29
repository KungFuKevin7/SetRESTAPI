package com.example.SetRESTAPI.api.controller;

import com.example.SetRESTAPI.api.dto.DeckCardDto;
import com.example.SetRESTAPI.api.service.CardsOnBoardService;
import com.example.SetRESTAPI.api.service.GameService;
import com.example.SetRESTAPI.api.service.HintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hint")
@CrossOrigin
public class HintController {

    @Autowired
    private HintService hintService;

    @PostMapping("/{gameId}")
    public ResponseEntity<List<DeckCardDto>> getHint(@PathVariable int gameId) {

        List<DeckCardDto> cardsOnBoard =
                hintService.getHint(gameId);
        return ResponseEntity.ok(cardsOnBoard);
    }
}
