package com.example.SetRESTAPI.api.controller;


import com.example.SetRESTAPI.api.dto.DeckCardDto;
import com.example.SetRESTAPI.api.dto.GameStateDto;
import com.example.SetRESTAPI.api.dto.SetDto;
import com.example.SetRESTAPI.api.dto.SetResponseDto;
import com.example.SetRESTAPI.api.model.Set;
import com.example.SetRESTAPI.api.service.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/set")
@CrossOrigin
public class SetController {

    @Autowired
    private SetService setService;

    @GetMapping
    public List<Set> getAllSets(){
        return setService.getAllSets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Set> getSetById(@PathVariable Long id){
        Optional<Set> set = setService.getSetById(id);
        return set.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.notFound().build()
        );
    }

    @PostMapping("/validate-for-game/{gameId}")
    public ResponseEntity<Boolean> validateSet(@RequestBody List<DeckCardDto> cards, @PathVariable int gameId){

        boolean isValidSet = setService.validateAndSaveSet(cards, gameId);

        if(isValidSet){
            return ResponseEntity.ok(true);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }

    @PostMapping("/handle-validation-with-new-board/{gameId}")
    public ResponseEntity<GameStateDto> handelNewCards(@RequestBody List<DeckCardDto> foundsSetCards, @PathVariable int gameId){
        GameStateDto gameStateDto = setService.handleNewBoard(gameId, foundsSetCards);
        return ResponseEntity.ok(gameStateDto);
    }


    @GetMapping("/found-in-game/{gameId}")
    public ResponseEntity<List<SetDto>> setsFoundInGame(@PathVariable int gameId){
        List<SetDto> foundSets = setService.getFoundSets(gameId);
        return ResponseEntity.ok(foundSets);
    }

}
