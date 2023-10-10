package com.example.bigbowlxp.service;

import com.example.bigbowlxp.dto.*;
import com.example.bigbowlxp.exception.ReservationNotFoundException;
import com.example.bigbowlxp.model.*;
import com.example.bigbowlxp.repository.AirhockeyRepository;
import com.example.bigbowlxp.repository.BowlingRepository;
import com.example.bigbowlxp.repository.DiningRepository;
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
    private final AirhockeyRepository airhockeyRepository;
    private final DiningRepository diningRepository;
    private final AllConverter allConverter;

    @Autowired
    public ReservationService(
            ReservationRepository reservationRepository,
            ReservationConverter reservationConverter,
            BowlingRepository bowlingRepository,
            AllConverter allConverter,
            AirhockeyRepository airhockeyRepository,
            DiningRepository diningRepository
    ){
        this.reservationRepository = reservationRepository;
        this.reservationConverter = reservationConverter;
        this.bowlingRepository = bowlingRepository;
        this.allConverter = allConverter;
        this.airhockeyRepository = airhockeyRepository;
        this.diningRepository = diningRepository;
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
        Optional<Airhockey> airhockey = airhockeyRepository.findByReservationId(reservationDTO.id());
        Optional<Dining> dining = diningRepository.findByReservationId(reservationDTO.id());

        if(bowling.isPresent()){
            all.bowling = bowling.get();
        } if(airhockey.isPresent()){
            all.airhockey = airhockey.get();
        } if(dining.isPresent()){
            all.dining = dining.get();
        }
        return allConverter.toDTO(all);
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
