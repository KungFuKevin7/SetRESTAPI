package com.example.SetRESTAPI.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthTokenDto {
/*    private int userid;*/
    private String username;
    private String token;
    private long expiresInMillis;

}
