package com.graccasoft.schoolinvoicing.service.impl;

import com.graccasoft.schoolinvoicing.dto.InvoiceDto;
import com.graccasoft.schoolinvoicing.dto.StudentDto;
import com.graccasoft.schoolinvoicing.model.Billable;
import com.graccasoft.schoolinvoicing.model.SchoolClass;
import com.graccasoft.schoolinvoicing.repository.BillableRepository;
import com.graccasoft.schoolinvoicing.repository.SchoolClassRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InvoiceServiceImplTest {

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    private InvoiceServiceImpl invoiceService;

    @Autowired
    private BillableRepository billableRepository;

    @Autowired
    private StudentServiceImpl studentService;
    private SchoolClass schoolClass;

    private static int STUDENTS_COUNT = 5;

    @BeforeAll
    public void setup(){

        //save class
        SchoolClass newSchoolClass = new SchoolClass();
        newSchoolClass.setDescription("ECD Orange");
        newSchoolClass.setIsActive(true);
        schoolClass = schoolClassRepository.save(newSchoolClass);

        //add billable items to class
        Billable billable1 = new Billable();
        billable1.setDescription("Item 1");
        billable1.setIsActive(true);
        billable1.setSchoolClass(schoolClass);
        billable1.setUnitPrice(BigDecimal.valueOf(10));

        Billable billable2 = new Billable();
        billable2.setDescription("Item 2");
        billable2.setIsActive(true);
        billable2.setSchoolClass(schoolClass);
        billable1.setUnitPrice(BigDecimal.valueOf(15));

        billableRepository.saveAll( Arrays.stream((new Billable[]{billable1,billable2} )).toList() );

        //save 5 random students
        for(int x=0; x< STUDENTS_COUNT; x++){
            StudentDto studentDto = StudentDto.builder()
                    .parentAddress("132 ABC Street")
                    .parentName("John Doe")
                    .lastName("Sam " + x)
                    .firstName("Joe" + x)
                    .parentPhoneNumber("77283823")
                    .schoolClassId(schoolClass.getId())
                    .dateOfBirth(new Date())
                    .build();

            studentService.saveStudent(studentDto);
        }

    }

    @Test
    @Order(1)
    void shouldGenerateInvoicesForAllStudents(){
        //generate invoices
        invoiceService.generateInvoiceForSchoolClass(schoolClass.getId(), "Test invoice");

        List<InvoiceDto> invoices = invoiceService.getSchoolClassInvoices(schoolClass.getId());

        assertEquals(invoices.size(), STUDENTS_COUNT);
    }

    @Test
    @Order(2)
    void shouldGenerateInvoiceForStudent(){
        StudentDto studentDto = StudentDto.builder()
                .parentAddress("132 ABC Street")
                .parentName("John Doe")
                .lastName("Sam ")
                .firstName("Joe")
                .parentPhoneNumber("77283823")
                .schoolClassId(schoolClass.getId())
                .dateOfBirth(new Date())
                .build();

        StudentDto savedStudent = studentService.saveStudent(studentDto);

        //generate invoices
        invoiceService.generateInvoiceForStudent(savedStudent.getId(), "Test invoice");

        List<InvoiceDto> invoices = invoiceService.getStudentInvoices(savedStudent.getId());
        Assertions.assertEquals(1, invoices.size());

    }

    @Test
    @Order(3)
    void shouldGenerateInvoiceWithCorrectItems(){
        StudentDto studentDto = StudentDto.builder()
                .parentAddress("132 ABC Street")
                .parentName("John Doe")
                .lastName("Sam ")
                .firstName("Joe")
                .parentPhoneNumber("77283823")
                .schoolClassId(schoolClass.getId())
                .dateOfBirth(new Date())
                .build();

        StudentDto savedStudent = studentService.saveStudent(studentDto);

        //generate invoices
        invoiceService.generateInvoiceForStudent(savedStudent.getId(), "Test invoice");

        List<InvoiceDto> invoices = invoiceService.getStudentInvoices(savedStudent.getId());
        Assertions.assertEquals(invoices.get(0).getItems().size(), 2);

    }
}