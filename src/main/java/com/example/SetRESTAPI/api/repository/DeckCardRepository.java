package com.example.SetRESTAPI.api.repository;

import com.example.SetRESTAPI.api.model.DeckCard;
import com.example.SetRESTAPI.api.model.Game;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeckCardRepository extends JpaRepository<DeckCard, Long> {
    List<DeckCard> getDeckCardsByGame(Game game);

    @Query(nativeQuery = true, value="SELECT * FROM deck_card WHERE deck_card.game_id=:gameId AND deck_card.status='In Deck'")
    List<DeckCard> getDeckCardsInDeckByGame(@Param("gameId") Integer gameId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE deck_card SET status = 'On Table' WHERE (card_id IN :cardIds) AND game_id=:gameId")
    void setDeckCardStatusToOnTable(@Param("cardIds")Integer[] cardIds, @Param("gameId") int gameId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE deck_card SET status = 'In Found Set' WHERE (card_id IN :cardIds) AND game_id=:gameId")
    void setDeckCardStatusToFoundSet(@Param("cardIds")Integer[] cardIds, @Param("gameId") int gameId);
}
