package com.example.SetRESTAPI.api.repository;

import com.example.SetRESTAPI.api.model.SetFound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetFoundRepository extends JpaRepository<SetFound, Long> {
}
