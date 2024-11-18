package com.example.SetRESTAPI.api.service;

import java.util.List;
import java.util.Optional;

import com.example.SetRESTAPI.api.model.SetFound;
import com.example.SetRESTAPI.api.repository.SetFoundRepository;
import com.example.SetRESTAPI.api.repository.SetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SetFoundService {
    private final SetFoundRepository setFoundRepository;

    @Autowired
    public SetFoundService(SetFoundRepository setFoundRepository) {
        this.setFoundRepository = setFoundRepository;
    }

    public List<SetFound> getAllSetsFound(){
        return setFoundRepository.findAll();
    }

    public Optional<SetFound> getSetsFoundById(Long id){
        return setFoundRepository.findById(id);
    }

    public SetFound addSetFound(SetFound setFound){
        return setFoundRepository.save(setFound);
    }

    public void deleteSetFound(Long id){
        if (setFoundRepository.existsById(id)) {
            setFoundRepository.deleteById(id);
        }else {
            throw new RuntimeException("Set does not exist");
        }
    }
}
