package com.graccasoft.schoolinvoicing.controller;

import com.graccasoft.schoolinvoicing.dto.GenericResponse;
import com.graccasoft.schoolinvoicing.service.InvoiceService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("student")
    public GenericResponse generateStudentInvoice(@RequestBody GenerateStudentInvoiceRequest generateStudentInvoiceRequest){
        invoiceService.generateInvoiceForStudent(generateStudentInvoiceRequest.studentId(),
                generateStudentInvoiceRequest.title());
        return new GenericResponse(true, "Invoices successfully generated");
    }

    @PostMapping("school")
    public GenericResponse generateSchoolClassInvoice(@RequestBody GenerateSchoolClassInvoiceRequest generateSchoolClassInvoiceRequest){
        invoiceService.generateInvoiceForSchoolClass(generateSchoolClassInvoiceRequest.schoolClassId(),
                generateSchoolClassInvoiceRequest.title());
        return new GenericResponse(true, "Invoices successfully generated");
    }



}
record GenerateStudentInvoiceRequest(Long studentId, String title){}
record GenerateSchoolClassInvoiceRequest(Long schoolClassId, String title){}
