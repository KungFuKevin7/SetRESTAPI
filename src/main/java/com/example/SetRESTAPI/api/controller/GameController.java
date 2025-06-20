package com.example.SetRESTAPI.api.controller;


import java.util.List;
import com.example.SetRESTAPI.api.model.Game;
import com.example.SetRESTAPI.api.model.Set;
import com.example.SetRESTAPI.api.model.UserPrincipal;
import com.example.SetRESTAPI.api.model.Users;
import com.example.SetRESTAPI.api.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
@CrossOrigin
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<Game> getAllGames(){
        return gameService.getAllGames();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id){
        return gameService.getGameById(id).
                map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.notFound().build()
        );
    }

    @GetMapping("/by-user")
    public ResponseEntity<List<Game>> getUserGamesById(@AuthenticationPrincipal UserPrincipal user){
        List<Game> games = gameService.getGamesByUser(user);
        if(games.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(games);
    }

    @PostMapping("/start-new")
    public ResponseEntity<Game> startNewGame(@RequestBody Users user) {
        Game startedGame = gameService.startGame(user);
        return new ResponseEntity<>(startedGame, HttpStatus.CREATED);
    }

    @PostMapping
    public Game addGame(@RequestBody Game game) {
        return gameService.addGame(game);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id){
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }


}
