package com.example.SetRESTAPI.api.repository;

import com.example.SetRESTAPI.api.model.Game;
import com.example.SetRESTAPI.api.model.Set;
import com.example.SetRESTAPI.api.model.Users;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByUsers_Userid(long usersUserid, Sort sort);
}
