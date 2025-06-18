package com.example.SetRESTAPI.api.repository;

import com.example.SetRESTAPI.api.model.DeckCard;
import com.example.SetRESTAPI.api.model.Game;
import com.example.SetRESTAPI.api.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeckCardRepository extends JpaRepository<DeckCard, Long> {
    List<DeckCard> getDeckCardsByGame(Game game);
}
