package com.example.SetRESTAPI.api.repository;

import com.example.SetRESTAPI.api.model.Set;
import com.example.SetRESTAPI.api.model.SetCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetCardRepository extends JpaRepository<SetCard, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM set_card WHERE set_id=:setId")
    List<SetCard> findAllBySetId(@Param("setId")int setId);
}
