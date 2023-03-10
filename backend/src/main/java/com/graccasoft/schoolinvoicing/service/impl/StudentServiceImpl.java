package com.graccasoft.schoolinvoicing.service.impl;

import com.graccasoft.schoolinvoicing.dto.*;
import com.graccasoft.schoolinvoicing.enums.TransactionType;
import com.graccasoft.schoolinvoicing.exception.BadRequestException;
import com.graccasoft.schoolinvoicing.model.SchoolClass;
import com.graccasoft.schoolinvoicing.model.Student;
import com.graccasoft.schoolinvoicing.repository.SchoolClassRepository;
import com.graccasoft.schoolinvoicing.repository.StudentRepository;
import com.graccasoft.schoolinvoicing.service.InvoiceService;
import com.graccasoft.schoolinvoicing.service.PaymentService;
import com.graccasoft.schoolinvoicing.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentDtoMapper studentDtoMapper;
    private final SchoolClassRepository schoolClassRepository;

    private final InvoiceService invoiceService;
    private final PaymentService paymentService;

    public StudentServiceImpl(StudentRepository studentRepository, StudentDtoMapper studentDtoMapper, SchoolClassRepository schoolClassRepository, InvoiceService invoiceService, PaymentService paymentService) {
        this.studentRepository = studentRepository;
        this.studentDtoMapper = studentDtoMapper;
        this.schoolClassRepository = schoolClassRepository;
        this.invoiceService = invoiceService;
        this.paymentService = paymentService;
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
        student.setParentAddress(studentDto.getParentAddress());
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

    @Override
    public StudentStatementDto getStudentStatement(Long studentId) {

        List<PaymentDto> payments = paymentService.getStudentPayments(studentId);
        List<InvoiceDto> invoices = invoiceService.getStudentInvoices(studentId);

        BigDecimal balance = BigDecimal.ZERO;

        List<StudentStatementItemDto> statementItems = new ArrayList<>();
        for(PaymentDto payment: payments){
            StudentStatementItemDto item = StudentStatementItemDto.builder()
                    .date(payment.getCreatedAt())
                    .description("Payment")
                    .amount(payment.getAmount())
                    .transactionType(TransactionType.PAYMENT)
                    .build();
            statementItems.add(item);

            if(payment.getAmount() != null) {
                balance = balance.subtract(payment.getAmount());
            }
        }
        for(InvoiceDto invoice: invoices){
            StudentStatementItemDto item = StudentStatementItemDto.builder()
                    .date(invoice.getCreatedAt())
                    .description("Invoice")
                    .amount(invoice.getTotalAmount())
                    .transactionType(TransactionType.INVOICE)
                    .build();
            statementItems.add(item);

            if(invoice.getTotalAmount() != null) {
                balance = balance.add(invoice.getTotalAmount());
            }
        };

        statementItems.sort(Comparator.comparing(StudentStatementItemDto::getDate));

        return StudentStatementDto.builder()
                .items(statementItems)
                .balance(balance)
                .studentId(studentId)
                .build();
    }
}
