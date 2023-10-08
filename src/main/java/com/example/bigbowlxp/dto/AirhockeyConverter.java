package com.example.bigbowlxp.dto;

import com.example.bigbowlxp.model.Airhockey;
import com.example.bigbowlxp.model.Bowling;

public class AirhockeyConverter {

    public Airhockey toEntity(AirhockeyDTO airhockeyDTO){

        return new Airhockey(
                airhockeyDTO.id(),
                airhockeyDTO.reservation(),
                airhockeyDTO.numberOfTables(),
                airhockeyDTO.localDateTime(),
                airhockeyDTO.playtime()
        );
    }

    public AirhockeyDTO toDTO(Airhockey airhockey){
        return new AirhockeyDTO(
                airhockey.getId(),
                airhockey.getReservation(),
                airhockey.getDateTime(),
                airhockey.getNumberOfTables(),
                airhockey.getPlaytime()
        );
    }

}
