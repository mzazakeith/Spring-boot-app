package com.app.springbootapp.controller;

import com.app.springbootapp.dto.StudentClassDTO;
import com.app.springbootapp.dto.StudentDTO;
import com.app.springbootapp.exception.StudentNotFoundException;
import com.app.springbootapp.model.Student;
import com.app.springbootapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping()
    public ResponseEntity<Student> saveStudent(@Valid @RequestBody Student student){
        return new ResponseEntity<Student>(studentService.saveStudent(student), HttpStatus.CREATED);
    }

    @GetMapping()
    public List<StudentDTO> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/students-classes")
    public List<StudentClassDTO> getAllStudentsClasses(){
        return studentService.getAllStudentsClasses();
    }

    @GetMapping("{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") long studentId) throws StudentNotFoundException {
        return new ResponseEntity<StudentDTO>(studentService.getStudentById(studentId), HttpStatus.OK);
    }

    @GetMapping("students-classes/{id}")
    public ResponseEntity<StudentClassDTO> getStudentsClassesById(@PathVariable("id") long studentId) throws StudentNotFoundException {
        return new ResponseEntity<StudentClassDTO>(studentService.getStudentsClassesById(studentId), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") long studentId, @RequestBody Student student) throws StudentNotFoundException {
        return new ResponseEntity<Student>(studentService.updateStudent(student,studentId),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") long studentId) throws StudentNotFoundException {
        studentService.deleteStudent(studentId);
        return new ResponseEntity<String>("Student removed from school database", HttpStatus.OK);
    }

}
