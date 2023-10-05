package com.example.bigbowlxp.controller;

import com.example.bigbowlxp.dto.ReservationDTO;
import com.example.bigbowlxp.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ReservationRestController {
    @Autowired
            //lav serviceklassen
    ReservationService reservationService;

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationDTO>> getAllReservations(){
        List<ReservationDTO> reservationDTOList = reservationService.getAllReservations();
        return new ResponseEntity<>(reservationDTOList, HttpStatus.OK);
    }

    @PostMapping("/reservation")
    public ResponseEntity<ReservationDTO> postReservation(@RequestBody ReservationDTO reservationDTO){
        ReservationDTO createdReservation = reservationService.createReservation(reservationDTO);
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }

    @GetMapping("/reservation/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable("id")int id){
        ReservationDTO reservationDTO = reservationService.getReservationById(id);
        return ResponseEntity.ok(reservationDTO);
    }

    @PutMapping("/reservation/{id}")
    public ResponseEntity<ReservationDTO> putReservation(@PathVariable("id") int id, @RequestBody ReservationDTO reservationDTO){
        ReservationDTO updatedReservationDTO = reservationService.updateReservation(id, reservationDTO);
        return ResponseEntity.ok(updatedReservationDTO);
    }

    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") int id){
        reservationService.deleteReservationById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
