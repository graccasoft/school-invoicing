package com.graccasoft.schoolinvoicing.controller;

import com.graccasoft.schoolinvoicing.dto.InvoiceDto;
import com.graccasoft.schoolinvoicing.dto.PaymentDto;
import com.graccasoft.schoolinvoicing.dto.StudentDto;
import com.graccasoft.schoolinvoicing.dto.StudentStatementDto;
import com.graccasoft.schoolinvoicing.service.InvoiceDocumentService;
import com.graccasoft.schoolinvoicing.service.InvoiceService;
import com.graccasoft.schoolinvoicing.service.PaymentService;
import com.graccasoft.schoolinvoicing.service.StudentService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final InvoiceService invoiceService;
    private final PaymentService paymentService;
    private final InvoiceDocumentService invoiceDocumentService;

    public StudentController(StudentService studentService, InvoiceService invoiceService, PaymentService paymentService, InvoiceDocumentService invoiceDocumentService) {
        this.studentService = studentService;
        this.invoiceService = invoiceService;
        this.paymentService = paymentService;
        this.invoiceDocumentService = invoiceDocumentService;
    }

    @PostMapping
    public ResponseEntity<StudentDto> saveStudent(@RequestBody StudentDto studentDto){
        StudentDto savedStudent = studentService.saveStudent(studentDto);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @GetMapping()
    public List<StudentDto> getStudents( @RequestParam(name = "lastName", required = false) String lastName){
        lastName = lastName == null ? "" : lastName;
        return studentService.findStudentsByName(lastName);
    }

    @GetMapping("/{id}")
    public StudentDto getStudent(@PathVariable Long id){
        return studentService.getStudent(id);
    }

    @GetMapping("/search/{query}")
    public List<StudentDto> searchStudents(@PathVariable String query){
        return studentService.findStudentsByName(query);
    }

    @GetMapping("{studentId}/invoices")
    public List<InvoiceDto> getStudentInvoices(@PathVariable Long studentId){
        return invoiceService.getStudentInvoices(studentId);
    }

    @GetMapping("{studentId}/invoice")
    public ResponseEntity<Object> getStudentInvoiceDocument(@PathVariable Long studentId) throws FileNotFoundException {
        Long invoiceId = invoiceService.getLatestStudentInvoice(studentId).getId();
        String pdfFileName = invoiceDocumentService.getInvoiceFileName(invoiceId);
        return ResponseEntity
                .ok()
                .header("Content-type","application/pdf")
                .body(new InputStreamResource( new FileInputStream(pdfFileName)));
    }

    @GetMapping("{studentId}/payments")
    public List<PaymentDto> getStudentPayments(@PathVariable Long studentId){
        return paymentService.getStudentPayments(studentId);
    }

    @GetMapping("{studentId}/statement")
    public StudentStatementDto getStudentStatement(@PathVariable Long studentId){
        return studentService.getStudentStatement(studentId);
    }



}
