package com.example.bigbowlxp.exception;

public class EmployeeScheduleNotFoundException extends RuntimeException {
    public EmployeeScheduleNotFoundException(String message){
        super(message);
    }
}
