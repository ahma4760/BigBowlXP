package com.example.bigbowlxp.dto;

import com.example.bigbowlxp.model.Airhockey;
import com.example.bigbowlxp.model.All;
import com.example.bigbowlxp.model.Bowling;
import org.springframework.stereotype.Component;

@Component
public class AllConverter {

    ReservationConverter reservationConverter = new ReservationConverter();
    AirhockeyConverter airhockeyConverter = new AirhockeyConverter();
    BowlingConverter bowlingConverter = new BowlingConverter();
    DiningConverter diningConverter = new DiningConverter();
    public All toEnity(AllDTO allDTO){

        return new All(
                allDTO.airhockeyDTO() == null ? null : airhockeyConverter.toEntity(allDTO.airhockeyDTO()),
                allDTO.bowlingDTO() == null ? null : bowlingConverter.toEntity(allDTO.bowlingDTO()),
                allDTO.diningDTO() == null ? null : diningConverter.toEntity(allDTO.diningDTO()),
                allDTO.reservationDTO() == null ? null : reservationConverter.toEntity(allDTO.reservationDTO())
        );
    }
    public AllDTO toDTO(All all){

        return new AllDTO(
                all.airhockey == null ? null : airhockeyConverter.toDTO(all.airhockey),
                all.bowling == null ? null : bowlingConverter.toDTO(all.bowling),
                all.dining == null ? null : diningConverter.toDTO(all.dining),
                all.reservation == null ? null : reservationConverter.toDTO(all.reservation)
        );
    }

}
