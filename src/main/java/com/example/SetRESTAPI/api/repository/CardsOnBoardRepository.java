package com.example.SetRESTAPI.api.repository;

import com.example.SetRESTAPI.api.model.CardsOnBoard;
import com.example.SetRESTAPI.api.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardsOnBoardRepository extends JpaRepository<CardsOnBoard, Long> {

    List<CardsOnBoard> getCardsOnBoardByGame(Game game);

    @Modifying
    @Query("DELETE FROM CardsOnBoard WHERE card_on_board_id IN :ids")
    void deleteSet(@Param("ids") List<Long> ids);
}
