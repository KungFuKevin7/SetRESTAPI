package com.example.SetRESTAPI.api.repository;

import com.example.SetRESTAPI.api.model.Card;
import com.example.SetRESTAPI.api.model.Game;
import com.example.SetRESTAPI.api.model.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetRepository extends JpaRepository<Set, Long> {
    List<Set> findByGame(Game game);

    long countByGame(Game game);
}
