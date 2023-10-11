package com.example.bigbowlxp.dto;

import com.example.bigbowlxp.model.Reservation;

import java.time.LocalDateTime;

public record DiningDTO(int id, Reservation reservation, String localDateTime, String tableReservation) {
}
