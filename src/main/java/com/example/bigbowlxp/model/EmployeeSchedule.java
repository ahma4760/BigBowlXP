package com.example.bigbowlxp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

//@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class EmployeeSchedule {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@ManyToOne
    //@JoinColumn(name = "id")
    private Employee employee;

    private LocalDateTime start_time;
    private LocalDateTime end_time;
}
