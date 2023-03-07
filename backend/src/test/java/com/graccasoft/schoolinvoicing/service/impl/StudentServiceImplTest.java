package com.graccasoft.schoolinvoicing.service.impl;

import com.graccasoft.schoolinvoicing.dto.StudentDto;
import com.graccasoft.schoolinvoicing.exception.BadRequestException;
import com.graccasoft.schoolinvoicing.model.SchoolClass;
import com.graccasoft.schoolinvoicing.repository.SchoolClassRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentServiceImplTest {

    private SchoolClass schoolClass;

    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @BeforeAll
    public void setup(){

        SchoolClass newSchoolClass = new SchoolClass();
        newSchoolClass.setDescription("ECD Orange");
        newSchoolClass.setIsActive(true);
        schoolClass = schoolClassRepository.save(newSchoolClass);

    }

    @Test
    public void shouldSaveStudent(){
        StudentDto studentDto = StudentDto.builder()
                .parentAddress("132 ABC Street")
                .parentName("John Doe")
                .lastName("Sam")
                .firstName("Joe")
                .parentPhoneNumber("77283823")
                .schoolClassId(schoolClass.getId())
                .dateOfBirth(new Date())

                .build();

        StudentDto savedStudent = studentService.saveStudent(studentDto);
        Assertions.assertNotNull( savedStudent.getId() );
    }

    @Test
    public void shouldSearchByClass(){
        StudentDto studentDto = StudentDto.builder()
                .parentAddress("132 ABC Street")
                .parentName("John Doe")
                .lastName("Sam")
                .firstName("Joe")
                .parentPhoneNumber("77283823")
                .schoolClassId(schoolClass.getId())
                .dateOfBirth(new Date())

                .build();

        studentService.saveStudent(studentDto);
        List<StudentDto> students = studentService.findStudentsInClass(schoolClass.getId());
        Assertions.assertTrue(students.size() > 0);
    }

    @Test
    public void shouldThrowExceptionIfInvalidClass(){
        StudentDto studentDto = StudentDto.builder()
                .parentAddress("132 ABC Street")
                .parentName("John Doe")
                .lastName("Sam")
                .firstName("Joe")
                .parentPhoneNumber("77283823")
                .schoolClassId(-100L)
                .dateOfBirth(new Date())
                .build();

        Assertions.assertThrows( BadRequestException.class, (()->studentService.saveStudent(studentDto)) )  ;

    }

    @Test
    public void shouldFindStudentByName(){
        StudentDto studentDto = StudentDto.builder()
                .parentAddress("132 ABC Street")
                .parentName("John Doe")
                .lastName("Sam")
                .firstName("Joe")
                .parentPhoneNumber("77283823")
                .schoolClassId(schoolClass.getId())
                .dateOfBirth(new Date())
                .build();

        studentService.saveStudent(studentDto);
        List<StudentDto> studentDtos = studentService.findStudentsByName("sam");
        Assertions.assertTrue(studentDtos.size() > 0);

    }

    @Test
    public void shouldNotFindStudentIfNameDoesntExist(){
        StudentDto studentDto = StudentDto.builder()
                .parentAddress("132 ABC Street")
                .parentName("John Doe")
                .lastName("Sam")
                .firstName("Joe")
                .parentPhoneNumber("77283823")
                .schoolClassId(schoolClass.getId())
                .dateOfBirth(new Date())
                .build();

        studentService.saveStudent(studentDto);
        List<StudentDto> studentDtos = studentService.findStudentsByName("chris");
        assertEquals(0, studentDtos.size());

    }
}