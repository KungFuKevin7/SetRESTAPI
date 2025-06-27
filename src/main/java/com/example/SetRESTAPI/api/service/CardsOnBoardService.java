package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.dto.DeckCardDto;
import com.example.SetRESTAPI.api.model.CardsOnBoard;
import com.example.SetRESTAPI.api.model.Card;
import com.example.SetRESTAPI.api.model.Game;
import com.example.SetRESTAPI.api.repository.CardsOnBoardRepository;
import com.example.SetRESTAPI.api.repository.GameRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CardsOnBoardService {

    private final CardsOnBoardRepository cardsOnBoardRepository;
    private final GameRepository gameRepository;

    @Autowired
    public CardsOnBoardService(CardsOnBoardRepository cardsOnBoardRepository, GameRepository gameRepository) {
        this.cardsOnBoardRepository = cardsOnBoardRepository;
        this.gameRepository = gameRepository;
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
    @Transactional
    public List<CardsOnBoard> addCardsOnBoard(List<DeckCardDto> cardsOnBoard, long gameId) {

        Game game = gameRepository.findById(gameId)
                .orElseThrow();

        List<CardsOnBoard> cardOnBoardItems = new ArrayList<>();

        for (var card : cardsOnBoard) {
            CardsOnBoard cardsOnBoardItem = new CardsOnBoard();
            cardsOnBoardItem.setGame(game);
            cardsOnBoardItem.setCard(card.convertToCard());
            cardOnBoardItems.add(cardsOnBoardItem);

        }

        return cardsOnBoardRepository.saveAll(cardOnBoardItems);
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
