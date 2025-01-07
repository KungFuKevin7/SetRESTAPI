package com.example.SetRESTAPI.api.repository;

import com.example.SetRESTAPI.api.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    @Query("SELECT UserId, Username FROM Users WHERE Username = ?1")
    Users findByUsername(String username);
}
