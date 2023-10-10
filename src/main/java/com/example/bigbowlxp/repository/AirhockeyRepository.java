package com.example.bigbowlxp.repository;

import com.example.bigbowlxp.model.Airhockey;
import com.example.bigbowlxp.model.Bowling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirhockeyRepository extends JpaRepository<Airhockey, Integer> {
    Optional<Airhockey> findByReservationId(int reservation_id);

}
