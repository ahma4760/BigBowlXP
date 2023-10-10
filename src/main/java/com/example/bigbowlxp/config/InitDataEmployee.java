package com.example.bigbowlxp.config;

import com.example.bigbowlxp.model.Employee;
import com.example.bigbowlxp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class InitDataEmployee implements CommandLineRunner {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        Employee e1 = new Employee();
        e1.setName("Ahmad Harbieh");

        employeeRepository.save(e1);
    }
}
