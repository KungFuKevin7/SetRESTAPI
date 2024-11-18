package com.example.SetRESTAPI.api.controller;


import com.example.SetRESTAPI.api.model.Card;
import com.example.SetRESTAPI.api.model.Set;
import com.example.SetRESTAPI.api.service.CardService;
import com.example.SetRESTAPI.api.service.SetService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Set createCard(@RequestBody Set set){
        return setService.addSet(set);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long id){
        setService.deleteSet(id);
        return ResponseEntity.noContent().build();
    }

}
