package com.app.springbootapp.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private Long studentId;
    private String firstName;
    private String lastName;
    private String email;
}
