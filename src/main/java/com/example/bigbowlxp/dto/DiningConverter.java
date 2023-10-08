package com.example.bigbowlxp.dto;

import com.example.bigbowlxp.model.Bowling;
import com.example.bigbowlxp.model.Dining;

public class DiningConverter {

    public Dining toEntity(DiningDTO diningDTO){

        return new Dining(
                diningDTO.id(),
                diningDTO.reservation(),
                diningDTO.table(),
                diningDTO.localDateTime()
        );
    }

    public DiningDTO toDTO(Dining dining) {
        return new DiningDTO(
                dining.getId(),
                dining.getReservation(),
                dining.getDateTime(),
                dining.getTable()
        );
    }
}
