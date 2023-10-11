package com.example.bigbowlxp.controller;

import com.example.bigbowlxp.dto.EmployeeDTO;
import com.example.bigbowlxp.repository.EmployeeRepository;
import com.example.bigbowlxp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
public class EmployeeRestController {
        @Autowired
        //lav serviceklassen
        EmployeeRepository employeeRepository;
        @Autowired
        EmployeeService employeeService;

        @GetMapping("/employees")
        public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
            List<EmployeeDTO> employeeDTOList = employeeService.getAllEmployees();
            return new ResponseEntity<>(employeeDTOList, HttpStatus.OK);
        }

        @PostMapping("/employee")
        public ResponseEntity<EmployeeDTO> postReservation(@RequestBody EmployeeDTO employeeDTO){
            EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO);
            return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
        }

        @GetMapping("/employee/{id}")
        public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id")int id){
            EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
            return ResponseEntity.ok(employeeDTO);
        }

        @PutMapping("/employee/{id}")
        public ResponseEntity<EmployeeDTO> putEmployee(@PathVariable("id") int id, @RequestBody EmployeeDTO employeeDTO){
            EmployeeDTO updatedEmployeeDTO = employeeService.updateEmployee(id, employeeDTO);
            return ResponseEntity.ok(updatedEmployeeDTO);
        }

        @DeleteMapping("/employee/{id}")
        public ResponseEntity<Void> deleteEmployee(@PathVariable("id") int id){
            employeeService.deleteEmployeeById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @GetMapping("/employee/{name}")
        public ResponseEntity<EmployeeDTO> getEmployeeByName(@PathVariable("name")String name){
            EmployeeDTO employeeDTO = employeeService.getEmployeeByName(name);
            return ResponseEntity.ok(employeeDTO);
        }

        @PutMapping("/employee/{name}")
        public ResponseEntity<EmployeeDTO> putEmployee(@PathVariable("name") String name, @RequestBody EmployeeDTO employeeDTO){
            //parse int pga navn erstattet med id
            EmployeeDTO updatedEmployeeDTO = employeeService.updateEmployee(Integer.parseInt(name), employeeDTO);
            return ResponseEntity.ok(updatedEmployeeDTO);
        }

        @DeleteMapping("/employee/{name}")
        public ResponseEntity<Void> deleteEmployee(@PathVariable("name") String name){
            employeeService.deleteEmployeeByName(name);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

}
