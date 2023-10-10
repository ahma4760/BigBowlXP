package com.example.bigbowlxp.dto;

import com.example.bigbowlxp.model.EmployeeSchedule;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class EmployeeScheduleConverter {
    public EmployeeScheduleDTO toDTO(EmployeeSchedule employeeSchedule) {
        return new EmployeeScheduleDTO(
                employeeSchedule.getId(),
                employeeSchedule.getEmployee().getName(),
                employeeSchedule.getStart_time().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                employeeSchedule.getEnd_time().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        );
    }
}
