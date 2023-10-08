package com.example.bigbowlxp.repository;

import com.example.bigbowlxp.model.All;
import com.example.bigbowlxp.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findAllByCustomerName(String customerName); //JPQL

    Optional<Reservation> findByCustomerName(String customerName);

    void deleteByCustomerName(String customerName);

    @Query(value = "SELECT * FROM reservation", nativeQuery = true)
    Stream<Reservation> findAllReservations();

    @Query(value = "SELECT * FROM bowling_db.reservation INNER JOIN  bowling_db.bowling ON bowling_db.reservation.id = bowling_db.bowling.reservation_id", nativeQuery = true)
    Stream<All> findAllAll();



}
