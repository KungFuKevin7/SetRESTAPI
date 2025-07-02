package com.example.SetRESTAPI.api.controller;

import com.example.SetRESTAPI.api.dto.DeckCardDto;
import com.example.SetRESTAPI.api.model.CardsOnBoard;
import com.example.SetRESTAPI.api.model.Game;
import com.example.SetRESTAPI.api.repository.CardsOnBoardRepository;
import com.example.SetRESTAPI.api.service.CardsOnBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards-on-board")
@CrossOrigin
public class CardsOnBoardController {

    @Autowired
    private CardsOnBoardService cardsOnBoardService;

    public CardsOnBoardController(CardsOnBoardService cardsOnBoardService) {
        this.cardsOnBoardService = cardsOnBoardService;
    }

    @GetMapping
    public List<CardsOnBoard> getAllCardsOnBoard(){
        return cardsOnBoardService.getAllCardsOnBoard();
    }

    @GetMapping("/by-game")
    public List<CardsOnBoard> getCardsOnBoardByGameId(@RequestBody Game game){
        return cardsOnBoardService.getCardsOnBoardByGame(game);
    }

    @PostMapping
    public CardsOnBoard addCardOnBoard(@RequestBody CardsOnBoard cardOnBoard){
        return cardsOnBoardService.addCardsOnBoard(cardOnBoard);
    }

    @PostMapping("/add-to/{gameId}")
    public ResponseEntity<List<CardsOnBoard>> addCardsOnBoard(@RequestBody List<DeckCardDto> cardsOnBoard, @PathVariable long gameId){
        return new ResponseEntity<>(
                cardsOnBoardService.addCardsOnBoard(cardsOnBoard, gameId),
                HttpStatus.CREATED);
    }

}
