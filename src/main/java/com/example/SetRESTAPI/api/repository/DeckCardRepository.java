package com.example.SetRESTAPI.api.repository;

import com.example.SetRESTAPI.api.model.DeckCard;
import com.example.SetRESTAPI.api.model.Game;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
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
    @Query(nativeQuery = true, value = "UPDATE deck_card SET status = 'In Deck' WHERE game_id IN :gameIds")
    List<DeckCard> setDeckCardsToInDeck(@Param("gameId") List<Integer> gameIds);
}
