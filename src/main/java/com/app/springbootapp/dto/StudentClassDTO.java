package com.app.springbootapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentClassDTO {
    private Long studentId;
    private String email;
    private List className;
}
