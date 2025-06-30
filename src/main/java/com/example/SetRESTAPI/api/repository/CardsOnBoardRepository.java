package com.example.SetRESTAPI.api.repository;

import com.example.SetRESTAPI.api.dto.DeckCardDto;
import com.example.SetRESTAPI.api.model.CardsOnBoard;
import com.example.SetRESTAPI.api.model.Game;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardsOnBoardRepository extends JpaRepository<CardsOnBoard, Long> {

    List<CardsOnBoard> getCardsOnBoardByGame(Game game);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM Cards_On_Board WHERE game_id = :gameId")
    void deleteCardsOnBoard(@Param("gameId") int gameId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM Cards_On_Board Bc WHERE Bc.card_id IN :cardIds AND Bc.game_id=:gameId")
    void deleteCardsOnBoardByCard(@Param("cardIds") Integer[] cardIds, @Param("gameId") int gameId);
}
