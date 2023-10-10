package com.example.bigbowlxp.dto;

import com.example.bigbowlxp.model.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationConverter {
    public Reservation toEntity(ReservationDTO reservationDTO){
        return new Reservation(
                reservationDTO.id(),
                reservationDTO.customerName()
        );
    }

    public ReservationDTO toDTO(Reservation reservation){
        return new ReservationDTO(
                reservation.getId(),
                reservation.getCustomerName()
        );
    }
}
