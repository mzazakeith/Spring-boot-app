package com.app.springbootapp.service;

import com.app.springbootapp.dto.StudentClassDTO;
import com.app.springbootapp.dto.StudentDTO;
import com.app.springbootapp.exception.StudentNotFoundException;
import com.app.springbootapp.model.Student;
import com.app.springbootapp.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<StudentDTO> getAllStudents(){
        return studentRepository.findAll().stream().map(this::modelMap).collect(Collectors.toList());

    }

    public StudentDTO getStudentById(long id) throws StudentNotFoundException {
        return studentRepository.findById(id).map(this::modelMap).orElseThrow(() -> new StudentNotFoundException("Student", "Id",id));
    }

    private StudentDTO modelMap(Student student){
        modelMapper.getConfiguration();
        StudentDTO studentDTO = new StudentDTO();
        studentDTO = modelMapper.map(student, StudentDTO.class);
        return studentDTO;

    }

    public List<StudentClassDTO> getAllStudentsClasses(){
        return studentRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public StudentClassDTO getStudentsClassesById(long id) throws StudentNotFoundException {
        return studentRepository.findById(id).map(this::convertEntityToDto).orElseThrow(() -> new StudentNotFoundException("Student", "Id",id));
    }

    private StudentClassDTO convertEntityToDto(Student student){
        StudentClassDTO studentClassDTO = new StudentClassDTO();
        studentClassDTO.setStudentId(student.getId());
        studentClassDTO.setEmail(student.getEmail());
        studentClassDTO.setClassName(student.getClassNames());
        return studentClassDTO;
    }

    public void deleteStudent(long id) throws StudentNotFoundException {
        studentRepository.findById(id).orElseThrow(() ->new StudentNotFoundException("Student", "Id",id));
        studentRepository.deleteById(id);
    }

    public Student updateStudent(Student student, long id) throws StudentNotFoundException {
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student", "Id", id));
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setPassword(student.getPassword());
        existingStudent.setClassNames(student.getClassNames());
        studentRepository.save(existingStudent);
        return existingStudent;
    }
}