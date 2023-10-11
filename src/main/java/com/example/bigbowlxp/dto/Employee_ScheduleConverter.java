package com.example.bigbowlxp.dto;

import com.example.bigbowlxp.model.Employee_Schedule;
import com.example.bigbowlxp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Employee_ScheduleConverter {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeConverter employeeConverter;
    public Employee_ScheduleDTO toDTO(Employee_Schedule employeeSchedule) {
        return new Employee_ScheduleDTO(
                employeeSchedule.getId(),
                employeeSchedule.getEmployee().getId(),
                employeeSchedule.getEmployee().getName(),
                employeeSchedule.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                employeeSchedule.getEndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        );
    }

    public Employee_Schedule toEntity(Employee_ScheduleDTO employeeScheduleDTO) {
        return new Employee_Schedule(
                employeeScheduleDTO.id(),
                employeeConverter.toEntity(employeeService.getEmployeeById(employeeScheduleDTO.userId())),
                LocalDateTime.parse(employeeScheduleDTO.fromDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                LocalDateTime.parse(employeeScheduleDTO.toDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        );
    }
}
