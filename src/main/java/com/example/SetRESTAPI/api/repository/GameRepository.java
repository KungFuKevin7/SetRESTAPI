package com.example.SetRESTAPI.api.repository;

import com.example.SetRESTAPI.api.model.Game;
import com.example.SetRESTAPI.api.model.Set;
import com.example.SetRESTAPI.api.model.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByUsers_Userid(long usersUserid, Sort sort);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE games SET status=:status WHERE game_id=:gameId")
    void updateGameStatusByGameId(@Param("status") String status, @Param("gameId")long gameId);
}
