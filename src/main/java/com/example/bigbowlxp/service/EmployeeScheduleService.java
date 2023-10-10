package com.example.bigbowlxp.service;

import com.example.bigbowlxp.dto.Employee_ScheduleConverter;
import com.example.bigbowlxp.dto.Employee_ScheduleDTO;
import com.example.bigbowlxp.model.Employee_Schedule;
import com.example.bigbowlxp.repository.EmployeeScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeScheduleService {
    private final EmployeeScheduleRepository employeeScheduleRepository;
    private final Employee_ScheduleConverter employeeScheduleConverter;

    @Autowired
    public EmployeeScheduleService(
            EmployeeScheduleRepository employeeScheduleRepository,
            Employee_ScheduleConverter employeeScheduleConverter
    ) {
        this.employeeScheduleRepository = employeeScheduleRepository;
        this.employeeScheduleConverter = employeeScheduleConverter;
    }

    public List<Employee_ScheduleDTO> getScheduleByDate(LocalDate date) {
        LocalDateTime startTime = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 0, 0);
        LocalDateTime endTime = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 23, 59);
        List<Employee_Schedule> schedule = employeeScheduleRepository.findEmployeeScheduleByEndTimeBetween(startTime, endTime);
        return schedule.stream()
                .map(employeeScheduleConverter::toDTO)
                .collect(Collectors.toList());
    }

    public Employee_ScheduleDTO createSchedule(Employee_ScheduleDTO employeeScheduleDTO) {
        Employee_Schedule employeeScheduleToSave = employeeScheduleConverter.toEntity(employeeScheduleDTO);
        //ensure it is a create
        employeeScheduleToSave.setId(0);
        Employee_Schedule savedEmployeeSchedule = employeeScheduleRepository.save(employeeScheduleToSave);
        return employeeScheduleConverter.toDTO(savedEmployeeSchedule);
    }
}
