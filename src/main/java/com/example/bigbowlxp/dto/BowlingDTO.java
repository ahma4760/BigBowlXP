package com.example.bigbowlxp.dto;

import com.example.bigbowlxp.model.Reservation;

import java.time.LocalDateTime;

public record BowlingDTO(int id, Reservation reservation, int numberOfAlleys,  String localDateTime, int playtime) {
}

