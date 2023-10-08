package com.example.bigbowlxp.repository;

import com.example.bigbowlxp.model.Bowling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface BowlingRepository extends JpaRepository<Bowling, Integer> {

    //@Query(value = "SELECT * FROM bowling_db.bowling WHERE reservation_id = ?1", nativeQuery = true)
    Optional<Bowling> findByReservationId(int reservation_id);

}
