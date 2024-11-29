package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.model.CardsOnBoard;
import com.example.SetRESTAPI.api.model.Game;
import com.example.SetRESTAPI.api.repository.CardsOnBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardsOnBoardService {

    private final CardsOnBoardRepository cardsOnBoardRepository;

    @Autowired
    public CardsOnBoardService(CardsOnBoardRepository cardsOnBoardRepository) {
        this.cardsOnBoardRepository = cardsOnBoardRepository;
    }

    public List<CardsOnBoard> getAllCardsOnBoard() {
        return cardsOnBoardRepository.findAll();
    }

    //Gets cards by Game Id, NOT by CardsOnBoardId
    public List<CardsOnBoard> getCardsOnBoardByGameId(Game game) {
        return cardsOnBoardRepository.getCardsOnBoardByGame(game);
    }

    public CardsOnBoard addCardsOnBoard(CardsOnBoard cardsOnBoard) {

        return cardsOnBoardRepository.save(cardsOnBoard);
    }

    //Adds the initial 12 cards to the database
    public List<CardsOnBoard> addCardsOnBoard(List<CardsOnBoard> cardsOnBoard) {
        return cardsOnBoardRepository.saveAll(cardsOnBoard);
    }

    //Removes a single card from the board
    public void deleteCardsOnBoard(Long Id) {
        if (cardsOnBoardRepository.existsById(Id)){
            cardsOnBoardRepository.deleteById(Id);
        } else{
            throw new RuntimeException("CardsOnBoard Item does not exist");
        }
    }

    //Removes a set, when user found a set
    public void deleteSetOnBoard(List<Long> foundSetIds) {
        // If cards were found, delete Set
        if (!cardsOnBoardRepository.findAllById(foundSetIds).isEmpty()) {
            cardsOnBoardRepository.deleteSet(foundSetIds);
        } else{
            throw new RuntimeException("Set could not be deleted");
        }
    }

}
