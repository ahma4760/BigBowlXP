package com.example.bigbowlxp.dto;

import com.example.bigbowlxp.model.Employee_Schedule;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class Employee_ScheduleConverter {
    public Employee_ScheduleDTO toDTO(Employee_Schedule employeeSchedule) {
        return new Employee_ScheduleDTO(
                employeeSchedule.getId(),
                employeeSchedule.getEmployee().getName(),
                employeeSchedule.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                employeeSchedule.getEndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        );
    }
}
