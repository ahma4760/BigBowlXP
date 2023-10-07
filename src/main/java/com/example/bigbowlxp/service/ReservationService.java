package com.example.bigbowlxp.service;

import com.example.bigbowlxp.dto.ReservationConverter;
import com.example.bigbowlxp.dto.ReservationDTO;
import com.example.bigbowlxp.exception.ReservationNotFoundException;
import com.example.bigbowlxp.model.Airhockey;
import com.example.bigbowlxp.model.Bowling;
import com.example.bigbowlxp.model.Dining;
import com.example.bigbowlxp.model.Reservation;
import com.example.bigbowlxp.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    //lav repo
    private final ReservationRepository reservationRepository;
    private final ReservationConverter reservationConverter;

    @Autowired
    public ReservationService(
            ReservationRepository reservationRepository,
            ReservationConverter reservationConverter
    ){
        this.reservationRepository = reservationRepository;
        this.reservationConverter = reservationConverter;
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
        reservationToSave.setId(0);
        reservationToSave.setName("Bowling Jens");
        Reservation savedReservation = reservationRepository.save(reservationToSave);
        return reservationConverter.toDTO(savedReservation);
    }

    @Transactional
    public ReservationDTO createReservationWithActivities(ReservationDTO reservationDTO) {
        //Create a Reservation entity and set its attributes
        Reservation reservation = new Reservation();
        reservation.setName(reservationDTO.getCustomerName());

        //Creates the activities and set there attributes
        Bowling bowling = new Bowling();
        bowling.setNumberOfAlley(reservationDTO.getNumberOfAlley());
        bowling.setDateTime(reservationDTO.getDateTime());
        bowling.setReservation(reservation); // Associate Bowling with the Reservation

        Dining dining = new Dining();
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
        reservation.setAirhockey(airhockey);

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
        Optional<Reservation> optionalReservation = reservationRepository.findByName(name);
        if(optionalReservation.isPresent()){
            return reservationConverter.toDTO(optionalReservation.get());
        } else{
            throw new ReservationNotFoundException("Reservation not found with name: " + name);
        }
    }


    public ReservationDTO updateReservation(String name, ReservationDTO reservationDTO){
        Optional<Reservation> existingReservation = reservationRepository.findByName(name);
        if(existingReservation.isPresent()){
            Reservation reservationToUpdate = reservationConverter.toEntity(reservationDTO);
            //ensure it is the id from the path that is updated
            reservationToUpdate.setName(name);
            Reservation savedReservation = reservationRepository.save(reservationToUpdate);
            return reservationConverter.toDTO(savedReservation);
        } else {
            throw new ReservationNotFoundException("Reservation  not found with name: " + name);
        }
    }
    public void deleteReservationByName(String name){
        Optional<Reservation> reservation = reservationRepository.findByName(name);
        if(reservation.isPresent()){
            reservationRepository.deleteByName(name);
        } else {
            throw new ReservationNotFoundException("Reservation not found with name: " + name);
        }
    }
}
