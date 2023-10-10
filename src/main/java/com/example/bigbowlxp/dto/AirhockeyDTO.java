package com.example.bigbowlxp.dto;

import com.example.bigbowlxp.model.Reservation;

import java.time.LocalDateTime;

public record AirhockeyDTO(int id, Reservation reservation, LocalDateTime localDateTime, int numberOfTables, int playtime) {


}
