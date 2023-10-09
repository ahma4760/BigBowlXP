package com.example.bigbowlxp.service;

import com.example.bigbowlxp.dto.EmployeeConverter;
import com.example.bigbowlxp.dto.EmployeeDTO;
import com.example.bigbowlxp.exception.EmployeeNotFoundException;
import com.example.bigbowlxp.model.Employee;
import com.example.bigbowlxp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    //lav repo
    private final EmployeeRepository employeeRepository;
    private final EmployeeConverter employeeConverter;

    @Autowired
    public EmployeeService(
            EmployeeRepository employeeRepository,
            EmployeeConverter employeeConverter
    ){
        this.employeeRepository = employeeRepository;
        this.employeeConverter = employeeConverter;
    }

    public List<EmployeeDTO> getAllEmployees(){
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employeeConverter::toDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(int id){
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent()){
            return employeeConverter.toDTO(optionalEmployee.get());
        } else{
            throw new EmployeeNotFoundException("Employee not found with id: " + id);
        }
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO){
        Employee employeeToSave = employeeConverter.toEntity(employeeDTO);
        //ensure it is a create
        employeeToSave.setId(0);
        employeeToSave.setName("Bowling Jens");
        Employee savedEmployee = employeeRepository.save(employeeToSave);
        return employeeConverter.toDTO(savedEmployee);
    }

    public EmployeeDTO updateEmployee(int id, EmployeeDTO employeeDTO){
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if(existingEmployee.isPresent()){
            Employee employeeToUpdate = employeeConverter.toEntity(employeeDTO);
            //ensure it is the id from the path that is updated
            employeeToUpdate.setId(id);
            Employee savedEmployee = employeeRepository.save(employeeToUpdate);
            return employeeConverter.toDTO(savedEmployee);
        } else {
            throw new EmployeeNotFoundException("Employee not found with id: " + id);
        }
    }
    public void deleteEmployeeById(int id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            employeeRepository.deleteById(id);
        } else {
            throw new EmployeeNotFoundException("Employee not found with id: " + id);
        }
    }
    public EmployeeDTO getEmployeeByName(String name){
        Optional<Employee> optionalEmployee = employeeRepository.findByName(name);
        if(optionalEmployee.isPresent()){
            return employeeConverter.toDTO(optionalEmployee.get());
        } else{
            throw new EmployeeNotFoundException("Employee not found with name: " + name);
        }
    }


    public EmployeeDTO updateEmployee(String name, EmployeeDTO employeeDTO){
        Optional<Employee> existingEmployee = employeeRepository.findByName(name);
        if(existingEmployee.isPresent()){
            Employee employeeToUpdate = employeeConverter.toEntity(employeeDTO);
            //ensure it is the id from the path that is updated
            employeeToUpdate.setName(name);
            Employee savedEmployee = employeeRepository.save(employeeToUpdate);
            return employeeConverter.toDTO(savedEmployee);
        } else {
            throw new EmployeeNotFoundException("Employee not found with name: " + name);
        }
    }
    public void deleteEmployeeByName(String name){
        Optional<Employee> employee = employeeRepository.findByName(name);
        if(employee.isPresent()){
            employeeRepository.deleteByName(name);
        } else {
            throw new EmployeeNotFoundException("Employee not found with name: " + name);
        }
    }
}