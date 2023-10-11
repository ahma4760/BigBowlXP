package com.example.bigbowlxp.repository;

import com.example.bigbowlxp.model.Employee_Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeScheduleRepository extends JpaRepository<Employee_Schedule, Integer> {
    List<Employee_Schedule> findEmployeeScheduleByEndTimeBetween(LocalDateTime start_time, LocalDateTime end_time);

    void deleteById(int id);

    Optional<Employee_Schedule> findById(int id);
}
