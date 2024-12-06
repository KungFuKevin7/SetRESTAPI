package com.example.SetRESTAPI.api.repository;

import com.example.SetRESTAPI.api.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query(value = "SELECT player_id, player_name FROM Player WHERE player_name = ?1")
    Optional<Player> findByPlayerName(String playerName);
}
