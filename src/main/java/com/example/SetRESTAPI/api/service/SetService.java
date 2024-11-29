package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.model.Card;
import com.example.SetRESTAPI.api.model.Set;
import com.example.SetRESTAPI.api.repository.CardRepository;
import com.example.SetRESTAPI.api.repository.SetRepository;
import com.example.SetRESTAPI.logic.SetLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SetService
{
    private final SetRepository setRepository;

    @Autowired
    public SetService(SetRepository setRepository) {
        this.setRepository = setRepository;
    }

    public List<Set> getAllSets(){
        return setRepository.findAll();
    }

    public Optional<Set> getSetById(Long id){
        return setRepository.findById(id);
    }

    public boolean checkSet(){
        return true;
    }

    public List<Set> addSet(List<Set> set){
        return setRepository.saveAll(set);
    }

    public void deleteSet(Long id){
        if (setRepository.existsById(id)){
            setRepository.deleteById(id);
        } else{
            throw new RuntimeException("Set does not exist");
        }
    }
}
