package com.example.bigbowlxp.controller;

import com.example.bigbowlxp.dto.Employee_ScheduleDTO;
import com.example.bigbowlxp.service.EmployeeScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
public class EmployeeScheduleRestController {

    @Autowired
    EmployeeScheduleService employeeScheduleService;

    @GetMapping("/schedule/{date}")
    public ResponseEntity<List<Employee_ScheduleDTO>> getEmployeeSchedule(@PathVariable("date") LocalDate date) {
        List<Employee_ScheduleDTO> result = employeeScheduleService.getScheduleByDate(date);
        return ResponseEntity.ok(result);
    }

}
