package com.example.SetRESTAPI.api.repository;

import com.example.SetRESTAPI.api.model.Card;
import com.example.SetRESTAPI.api.model.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetRepository extends JpaRepository<Set, Long> {
}
