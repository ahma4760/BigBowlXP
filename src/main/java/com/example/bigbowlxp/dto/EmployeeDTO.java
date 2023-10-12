package com.example.bigbowlxp.dto;

// TODO: just noticed that password is included in this DTO. Leaving it for now, as I don't want to break anything.
public record EmployeeDTO(int id, String name, String password, boolean isAdmin) {

}
