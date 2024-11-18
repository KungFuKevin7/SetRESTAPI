package com.example.SetRESTAPI.api.controller;


import com.example.SetRESTAPI.api.model.Set;
import com.example.SetRESTAPI.api.model.SetFound;
import com.example.SetRESTAPI.api.service.SetFoundService;
import com.example.SetRESTAPI.api.service.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/set-found")
@CrossOrigin
public class SetFoundController {

    @Autowired
    private SetFoundService setFoundService;

    @GetMapping
    public List<SetFound> getAllSetsFound(){
        return setFoundService.getAllSetsFound();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SetFound> getSetsFoundById(@PathVariable Long id){
        Optional<SetFound> setFound = setFoundService.getSetsFoundById(id);
        return setFound.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.notFound().build()
        );
    }

    @PostMapping
    public SetFound createSetFound(@RequestBody SetFound setFound){
        return setFoundService.addSetFound(setFound);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSetFound(@PathVariable Long id){
        setFoundService.deleteSetFound(id);
        return ResponseEntity.noContent().build();
    }
}
