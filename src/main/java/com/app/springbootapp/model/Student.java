package com.app.springbootapp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(min =2, message="should be at least 2 characters")
    @Column(name="first_name")
    private String firstName;
    @NotEmpty
    @Size(min =2, message="should be at least 2 characters")
    @Column(name="last_name")
    private String lastName;

    @NotEmpty
    @Size(min =8, message="should be at least 8 characters")
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    List<Class> classNames = new ArrayList<>();


}
