package com.example.bigbowlxp.controller;

import com.example.bigbowlxp.dto.EmployeeScheduleDTO;
import com.example.bigbowlxp.model.EmployeeSchedule;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class EmployeeScheduleRestController {

    @GetMapping("/schedule/{date}")
    public ResponseEntity<List<EmployeeScheduleDTO>> getEmployeeSchedule(@PathVariable("date") LocalDate date) {

        // TODO: Replace this when database table discrepancy has been resolved (user vs employee)

        List<EmployeeScheduleDTO> returnList = new ArrayList<EmployeeScheduleDTO>();
        returnList.add(new EmployeeScheduleDTO(
                1,
                "Yahya",
                "2023-10-01 12:30",
                "2023-10-01 17:00"));

        returnList.add(new EmployeeScheduleDTO(
                2,
                "Mikael",
                "2023-10-01 09:00",
                "2023-10-01 17:00"));

        returnList.add(new EmployeeScheduleDTO(
                3,
                "Elias",
                "2023-10-01 08:00",
                "2023-10-01 16:00"));

        return ResponseEntity.ok(returnList);
    }

}
