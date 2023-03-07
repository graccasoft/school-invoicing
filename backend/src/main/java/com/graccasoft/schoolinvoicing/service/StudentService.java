package com.graccasoft.schoolinvoicing.service;

import com.graccasoft.schoolinvoicing.dto.StudentDto;
import com.graccasoft.schoolinvoicing.model.Student;

import java.util.List;

public interface StudentService {
    StudentDto saveStudent(StudentDto studentDto);
    List<StudentDto> findStudentsInClass(Integer schoolClassId);
    List<StudentDto> findStudentsByName(String lastName);
    StudentDto getStudent(Integer studentId);
}
