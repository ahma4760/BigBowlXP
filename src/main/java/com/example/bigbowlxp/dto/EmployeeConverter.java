package com.example.bigbowlxp.dto;

import com.example.bigbowlxp.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter {
    public Employee toEntity(EmployeeDTO employeeDTO){
        return new Employee(
                employeeDTO.id(),
                employeeDTO.name(),
                employeeDTO.password(),
                employeeDTO.isAdmin()
        );
    }

    public EmployeeDTO toDTO(Employee employee){
        return new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getPassword(),
                employee.isAdmin()
        );
    }
}