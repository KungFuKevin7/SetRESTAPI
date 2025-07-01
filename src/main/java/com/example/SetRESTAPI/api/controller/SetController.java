package com.example.SetRESTAPI.api.controller;


import com.example.SetRESTAPI.api.dto.DeckCardDto;
import com.example.SetRESTAPI.api.dto.GameStateDto;
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

    @PostMapping
    public List<Set> addSet(@RequestBody List<Set> set){
        return setService.addSet(set);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long id){
        setService.deleteSet(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/validate-for-game/{gameId}")
    public ResponseEntity<SetResponseDto> validateSet(@RequestBody List<DeckCardDto> cards, @PathVariable int gameId){

        SetResponseDto set = setService.validateAndSaveSet(cards, gameId);

        if(set.isSetValid()){
            return ResponseEntity.ok(set);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(set);
        }
    }

    @PostMapping("/handle-new-cards/{gameId}")
    public ResponseEntity<GameStateDto> handelNewCards(@RequestBody List<DeckCardDto> foundsSetCards, @PathVariable int gameId){
        GameStateDto gameStateDto = setService.handleNewBoard(gameId, foundsSetCards);
        return ResponseEntity.ok(gameStateDto);
    }

}
