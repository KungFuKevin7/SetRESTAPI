package com.example.SetRESTAPI.api.dto;
import com.example.SetRESTAPI.api.model.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


public class SetDto {

    @Getter
    @Setter
    private int setId;

    @Getter
    @Setter
    private int setNumber;

    @Getter
    @Setter
    private List<DeckCardDto> cards;
}
