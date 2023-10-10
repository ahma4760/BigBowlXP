package com.example.bigbowlxp.config;

import com.example.bigbowlxp.model.Reservation;
import com.example.bigbowlxp.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class InitData implements CommandLineRunner {
    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public void run(String... args) throws Exception {
        Reservation r1 = new Reservation();
        r1.setCustomerName("Jens Bowling");

        reservationRepository.save(r1);
    }
}
