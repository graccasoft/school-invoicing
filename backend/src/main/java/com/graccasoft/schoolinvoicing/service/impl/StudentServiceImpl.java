package com.graccasoft.schoolinvoicing.service.impl;

import com.graccasoft.schoolinvoicing.dto.StudentDto;
import com.graccasoft.schoolinvoicing.dto.StudentDtoMapper;
import com.graccasoft.schoolinvoicing.exception.BadRequestException;
import com.graccasoft.schoolinvoicing.model.SchoolClass;
import com.graccasoft.schoolinvoicing.model.Student;
import com.graccasoft.schoolinvoicing.repository.SchoolClassRepository;
import com.graccasoft.schoolinvoicing.repository.StudentRepository;
import com.graccasoft.schoolinvoicing.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentDtoMapper studentDtoMapper;
    private final SchoolClassRepository schoolClassRepository;

    public StudentServiceImpl(StudentRepository studentRepository, StudentDtoMapper studentDtoMapper, SchoolClassRepository schoolClassRepository) {
        this.studentRepository = studentRepository;
        this.studentDtoMapper = studentDtoMapper;
        this.schoolClassRepository = schoolClassRepository;
    }


    @Override
    @Transactional
    public StudentDto saveStudent(StudentDto studentDto) {
        Student student = new Student();
        if(studentDto.getId() != null && studentDto.getId() != 0)
            student = studentRepository.getReferenceById(studentDto.getId());

        SchoolClass schoolClass = schoolClassRepository.findById(studentDto.getSchoolClassId())
                .orElseThrow(() ->new BadRequestException("Invalid class provided"));

        student.setDateOfBirth(studentDto.getDateOfBirth());
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setParentName(studentDto.getParentName());
        student.setParentPhoneNumber(studentDto.getParentPhoneNumber());
        student.setParentAddress(student.getParentAddress());
        student.setSchoolClass(  schoolClass   );

        return studentDtoMapper.apply( studentRepository.save(student) );
    }

    @Override
    public List<StudentDto> findStudentsInClass(Long schoolClassId) {
        SchoolClass schoolClass = schoolClassRepository.findById(schoolClassId)
                .orElseThrow(()-> new BadRequestException("Invalid school class"));
        //todo provide pages
        return  studentRepository.findAllBySchoolClass(schoolClass, PageRequest.of(0,20))
                .stream()
                .map(studentDtoMapper)
                .toList();
    }

    @Override
    public List<StudentDto> findStudentsByName(String lastName) {
        //todo use dynamic pagination
        return studentRepository.findAllByLastNameContainingIgnoreCase(lastName, PageRequest.of(0,20))
                .stream()
                .map(studentDtoMapper)
                .toList();
    }

    @Override
    public StudentDto getStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
        return studentDtoMapper.apply(student);
    }
}
