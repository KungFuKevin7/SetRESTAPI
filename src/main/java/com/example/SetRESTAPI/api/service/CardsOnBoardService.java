package com.example.SetRESTAPI.api.service;

import com.example.SetRESTAPI.api.converter.CardsOnBoardConverter;
import com.example.SetRESTAPI.api.dto.DeckCardDto;
import com.example.SetRESTAPI.api.model.CardsOnBoard;
import com.example.SetRESTAPI.api.model.Card;
import com.example.SetRESTAPI.api.model.Game;
import com.example.SetRESTAPI.api.repository.CardsOnBoardRepository;
import com.example.SetRESTAPI.api.repository.GameRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    public List<CardsOnBoard> getCardsOnBoardByGame(Game game) {
        return cardsOnBoardRepository.getCardsOnBoardByGame(game, Sort.by(Sort.Direction.ASC, "boardPosition"));
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

        for (int i = 0; i < cardsOnBoard.size(); i++) {
            DeckCardDto cardsOnBoardItemDto = cardsOnBoard.get(i);

            CardsOnBoard cardsOnBoardItem =
                    new CardsOnBoardConverter().convertToCardsOnBoard(cardsOnBoardItemDto, i, game);

            cardOnBoardItems.add(cardsOnBoardItem);
        }

        return cardsOnBoardRepository.saveAll(cardOnBoardItems);
    }

    public List<DeckCardDto> getCurrentCardsOnBoard(long gameId) {

        Game game = gameRepository.findById(gameId).orElseThrow();
        List<CardsOnBoard> cardsOnBoards = cardsOnBoardRepository.getCardsOnBoardByGame(game,
                Sort.by(Sort.Direction.ASC, "boardPosition"));

        return new CardsOnBoardConverter().convertList(cardsOnBoards);
    }

}
