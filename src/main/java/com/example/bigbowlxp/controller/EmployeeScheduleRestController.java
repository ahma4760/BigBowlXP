package com.example.bigbowlxp.controller;

import com.example.bigbowlxp.dto.EmployeeDTO;
import com.example.bigbowlxp.dto.Employee_ScheduleDTO;
import com.example.bigbowlxp.service.EmployeeScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/schedule")
    public ResponseEntity<Employee_ScheduleDTO> createEmployeeSchedule(@RequestBody Employee_ScheduleDTO employeeScheduleDTO) {
        Employee_ScheduleDTO createdSchedule = employeeScheduleService.createSchedule(employeeScheduleDTO);
        return ResponseEntity.ok(createdSchedule);
    }

    @PutMapping("/schedule")
    public ResponseEntity<Employee_ScheduleDTO> updateEmployeeSchedule(@RequestBody Employee_ScheduleDTO employeeScheduleDTO) {
        Employee_ScheduleDTO updatedSchedule = employeeScheduleService.updateEmployeeSchedule(employeeScheduleDTO);
        return ResponseEntity.ok(updatedSchedule);
    }

    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable("id") int id) {
        employeeScheduleService.deleteEmployeeSchedule(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
