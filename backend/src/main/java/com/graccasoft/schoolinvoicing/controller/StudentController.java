package com.graccasoft.schoolinvoicing.controller;

import com.graccasoft.schoolinvoicing.dto.StudentDto;
import com.graccasoft.schoolinvoicing.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentDto> saveStudent(@RequestBody StudentDto studentDto){
        StudentDto savedStudent = studentService.saveStudent(studentDto);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @GetMapping
    public List<StudentDto> getStudents(@PathVariable Long schoolClassId){
        return studentService.findStudentsInClass(schoolClassId);
    }

    @GetMapping("/{id}")
    public StudentDto getStudent(@PathVariable Long id){
        return studentService.getStudent(id);
    }

    @GetMapping("/search/{query}")
    public List<StudentDto> searchStudents(@PathVariable String query){
        return studentService.findStudentsByName(query);
    }

}
