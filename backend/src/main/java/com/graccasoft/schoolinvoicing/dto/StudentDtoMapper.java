package com.graccasoft.schoolinvoicing.dto;

import com.graccasoft.schoolinvoicing.model.Student;

import java.util.function.Function;

public class StudentDtoMapper implements Function<Student, StudentDto> {
    @Override
    public StudentDto apply(Student student) {

        return StudentDto.builder()
                .schoolClassName(student.getSchoolClass().getDescription())
                .id(student.getId())
                .parentName(student.getParentName())
                .parentPhoneNumber(student.getParentPhoneNumber())
                .dateOfBirth(student.getDateOfBirth())
                .createdAt(student.getCreatedAt())
                .parentAddress(student.getParentAddress())
                .schoolClassId(student.getSchoolClass().getId().toString())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .build();
    }
}
