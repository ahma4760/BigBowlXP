package com.example.bigbowlxp.repository;

import com.example.bigbowlxp.model.Airhockey;
import com.example.bigbowlxp.model.Dining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiningRepository extends JpaRepository<Dining, Integer> {
    Optional<Dining> findByReservationId(int reservation_id);
}
