package com.example.bigbowlxp.service;

import com.example.bigbowlxp.dto.*;
import com.example.bigbowlxp.exception.ReservationNotFoundException;
import com.example.bigbowlxp.model.*;
import com.example.bigbowlxp.repository.BowlingRepository;
import com.example.bigbowlxp.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ReservationService {
    //lav repo
    private final ReservationRepository reservationRepository;
    private final ReservationConverter reservationConverter;
    private final BowlingRepository bowlingRepository;
    private final AllConverter allConverter;

    @Autowired
    public ReservationService(
            ReservationRepository reservationRepository,
            ReservationConverter reservationConverter,
            BowlingRepository bowlingRepository,
            AllConverter allConverter
    ){
        this.reservationRepository = reservationRepository;
        this.reservationConverter = reservationConverter;
        this.bowlingRepository = bowlingRepository;
        this.allConverter = allConverter;
    }

    public List<ReservationDTO> getAllReservations(){
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(reservationConverter::toDTO)
                .collect(Collectors.toList());
    }


    public ReservationDTO getReservationById(int id){
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if(optionalReservation.isPresent()){
            return reservationConverter.toDTO(optionalReservation.get());
        } else{
            throw new ReservationNotFoundException("Reservation not found with id: " + id);
        }
    }

    public ReservationDTO createReservation(ReservationDTO reservationDTO){
        Reservation reservationToSave = reservationConverter.toEntity(reservationDTO);
        //ensure it is a create
        Reservation savedReservation = reservationRepository.save(reservationToSave);
        return reservationConverter.toDTO(savedReservation);
    }

    public AllDTO getReservationsWithActivities(ReservationDTO reservationDTO){
        All all = new All();
        all.reservation = reservationConverter.toEntity(reservationDTO);
        Optional<Bowling> bowling = bowlingRepository.findByReservationId(reservationDTO.id());
        // tilføj airhockey (det samme som bowling)
        // tilføj dining (det samme som bowling)
        if(bowling.isPresent()){
            all.bowling = bowling.get();
        } // tilføj airhockey og dining ligesom bowling
        return allConverter.toDTO(all);
    }

    @Transactional
    public ReservationDTO createReservationWithActivities(ReservationDTO reservationDTO, BowlingDTO bowlingDTO) {
        //Create a Reservation entity and set its attributes
        System.out.println("ReservationService, createReservationWithActivities: "+bowlingDTO+" "+reservationDTO);
        Reservation reservation = new Reservation();
        reservation.setCustomerName(reservationDTO.customerName());

        //Creates the activities and set there attributes
        Bowling bowling = new Bowling();
        bowling.setNumberOfAlleys(bowlingDTO.numberOfAlleys());
        bowling.setDateTime(bowlingDTO.localDateTime());
        bowling.setReservation(reservation); // Associate Bowling with the Reservation

       /* Dining dining = new Dining();
        dining.setTable(reservationDTO.getTable());
        dining.setDateTime(reservationDTO.getDateTime());
        dining.setReservation(reservation); // Associate Dining with the Reservation

        Airhockey airhockey = new Airhockey();
        airhockey.setNumberOfTables(reservationDTO.getNumberOfTables());
        airhockey.setDateTime(reservationDTO.getDateTime());
        airhockey.setReservation(reservation); // Associate Airhockey with the Reservation

        // Set associations between Reservation and its activities
        reservation.setBowling(bowling);
        reservation.setDining(dining);
        reservation.setAirhockey(airhockey);*/

        // Save the Reservation entity, which will also cascade-save related entities
        Reservation savedReservation = reservationRepository.save(reservation);

        // Convert the saved Reservation entity to a DTO and return it
        ReservationDTO createdReservationDTO = reservationConverter.toDTO(savedReservation);

        return createdReservationDTO;
    }

    public ReservationDTO updateReservation(int id, ReservationDTO reservationDTO){
        Optional<Reservation> existingReservation = reservationRepository.findById(id);
        if(existingReservation.isPresent()){
            Reservation reservationToUpdate = reservationConverter.toEntity(reservationDTO);
            //ensure it is the id from the path that is updated
            reservationToUpdate.setId(id);
            Reservation savedReservation = reservationRepository.save(reservationToUpdate);
            return reservationConverter.toDTO(savedReservation);
        } else {
            throw new ReservationNotFoundException("Reservation  not found with id: " + id);
        }
    }
    public void deleteReservationById(int id){
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if(reservation.isPresent()){
            reservationRepository.deleteById(id);
        } else {
            throw new ReservationNotFoundException("Reservation not found with id: " + id);
        }
    }
    public ReservationDTO getReservationByName(String name){
        Optional<Reservation> optionalReservation = reservationRepository.findByCustomerName(name);
        if(optionalReservation.isPresent()){
            return reservationConverter.toDTO(optionalReservation.get());
        } else{
            throw new ReservationNotFoundException("Reservation not found with name: " + name);
        }
    }


    public ReservationDTO updateReservation(String name, ReservationDTO reservationDTO){
        Optional<Reservation> existingReservation = reservationRepository.findByCustomerName(name);
        if(existingReservation.isPresent()){
            Reservation reservationToUpdate = reservationConverter.toEntity(reservationDTO);
            //ensure it is the id from the path that is updated
            reservationToUpdate.setCustomerName(name);
            Reservation savedReservation = reservationRepository.save(reservationToUpdate);
            return reservationConverter.toDTO(savedReservation);
        } else {
            throw new ReservationNotFoundException("Reservation  not found with name: " + name);
        }
    }
    public void deleteReservationByName(String name){
        Optional<Reservation> reservation = reservationRepository.findByCustomerName(name);
        if(reservation.isPresent()){
            reservationRepository.deleteByCustomerName(name);
        } else {
            throw new ReservationNotFoundException("Reservation not found with name: " + name);
        }
    }
}
