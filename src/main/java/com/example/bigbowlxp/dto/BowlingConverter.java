package com.example.bigbowlxp.dto;

import com.example.bigbowlxp.model.Bowling;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BowlingConverter {
    public Bowling toEntity(BowlingDTO bowlingDTO){

        return new Bowling(
                bowlingDTO.id(),
                bowlingDTO.reservation(),
                bowlingDTO.numberOfAlleys(),
                LocalDateTime.parse(bowlingDTO.localDateTime()),
                bowlingDTO.playtime()
        );
    }

    public BowlingDTO toDTO(Bowling bowling){
         return new BowlingDTO(
                 bowling.getId(),
                 bowling.getReservation(),
                 bowling.getNumberOfAlleys(),
                 bowling.getDateTime().toString(),
                 bowling.getPlaytime()
         );
    }

}
