package com.example.bigbowlxp.dto;

import com.example.bigbowlxp.model.Bowling;
import com.example.bigbowlxp.model.Dining;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DiningConverter {

    public Dining toEntity(DiningDTO diningDTO){

        return new Dining(
                diningDTO.id(),
                diningDTO.reservation(),
                diningDTO.tableReservation(),
                LocalDateTime.parse(diningDTO.localDateTime())
        );
    }

    public DiningDTO toDTO(Dining dining) {
        return new DiningDTO(
                dining.getId(),
                dining.getReservation(),
                dining.getDateTime().toString(),
                dining.getTableReservation()
        );
    }
}
