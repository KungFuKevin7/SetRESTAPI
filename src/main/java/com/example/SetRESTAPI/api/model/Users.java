package com.example.SetRESTAPI.api.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import java.util.Date;

@Entity
@Table(name = "Users")
public class Users {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UserId;

    @Setter
    @Getter
    @Column(unique = true, nullable = false)
    private String Username;

    @Setter
    @Getter
    @Column(nullable = false)
    private String Password;

}
