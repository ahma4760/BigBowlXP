package com.example.bigbowlxp.dto;

import com.example.bigbowlxp.model.Airhockey;
import com.example.bigbowlxp.model.All;
import com.example.bigbowlxp.model.Bowling;
import org.springframework.stereotype.Component;

@Component
public class AllConverter {

    ReservationConverter reservationConverter;
    AirhockeyConverter airhockeyConverter;
    BowlingConverter bowlingConverter;
    DiningConverter diningConverter;
    public All toEnity(AllDTO allDTO){

        return new All(
                airhockeyConverter.toEntity(allDTO.airhockeyDTO()),
                bowlingConverter.toEntity(allDTO.bowlingDTO()),
                diningConverter.toEntity(allDTO.diningDTO()),
                reservationConverter.toEntity(allDTO.reservationDTO())
        );
    }
    public AllDTO toDTO(All all){

        return new AllDTO(
                airhockeyConverter.toDTO(all.airhockey),
                bowlingConverter.toDTO(all.bowling),
                diningConverter.toDTO(all.dining),
                reservationConverter.toDTO(all.reservation)
        );
    }

}
