package com.example.SetRESTAPI.api.repository;

import com.example.SetRESTAPI.api.controller.CardController;
import com.example.SetRESTAPI.api.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.Optional;


@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query(value = "SELECT * FROM CARDS ORDER BY RANDOM() LIMIT 12", nativeQuery = true)
    List<Card> getRandomTableCards();
}
