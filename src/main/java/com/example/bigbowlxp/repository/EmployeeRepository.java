package com.example.bigbowlxp.repository;

import com.example.bigbowlxp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Integer>{
        //List<Employee> findAllByName (String name);
        Optional<Employee> findByName(String name);
        void deleteByName(String name);
        List<Employee> findAll();
}