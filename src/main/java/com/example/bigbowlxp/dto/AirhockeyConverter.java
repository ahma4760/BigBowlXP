package com.example.bigbowlxp.dto;

import com.example.bigbowlxp.model.Airhockey;
import com.example.bigbowlxp.model.Bowling;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AirhockeyConverter {

    public Airhockey toEntity(AirhockeyDTO airhockeyDTO){

        return new Airhockey(
                airhockeyDTO.id(),
                airhockeyDTO.reservation(),
                airhockeyDTO.numberOfTables(),
                LocalDateTime.parse(airhockeyDTO.localDateTime()),
                airhockeyDTO.playtime()
        );
    }

    public AirhockeyDTO toDTO(Airhockey airhockey){
        return new AirhockeyDTO(
                airhockey.getId(),
                airhockey.getReservation(),
                airhockey.getDateTime().toString(),
                airhockey.getNumberOfTables(),
                airhockey.getPlaytime()
        );
    }

}
