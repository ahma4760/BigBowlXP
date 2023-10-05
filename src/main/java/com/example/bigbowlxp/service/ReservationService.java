package com.example.bigbowlxp.service;

import com.example.bigbowlxp.dto.ReservationConverter;
import com.example.bigbowlxp.dto.ReservationDTO;
import com.example.bigbowlxp.exception.ReservationNotFoundException;
import com.example.bigbowlxp.model.Reservation;
import com.example.bigbowlxp.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Reservation savedReservation = reservationRepository.save(reservationToSave);
        return reservationConverter.toDTO(savedReservation);
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
}
