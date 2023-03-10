package com.graccasoft.schoolinvoicing.controller;

import com.graccasoft.schoolinvoicing.dto.GenericResponse;
import com.graccasoft.schoolinvoicing.service.InvoiceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("student")
    public GenericResponse generateStudentInvoice(@RequestBody GenerateStudentInvoiceRequest generateStudentInvoiceRequest){
        invoiceService.generateInvoiceForStudent(generateStudentInvoiceRequest.studentId);
        return new GenericResponse(true, "Invoices successfully generated");
    }

    @PostMapping("school")
    public GenericResponse generateSchoolClassInvoice(@RequestBody GenerateSchoolClassInvoiceRequest generateSchoolClassInvoiceRequest){
        invoiceService.generateInvoiceForSchoolClass(generateSchoolClassInvoiceRequest.schoolClassId());
        return new GenericResponse(true, "Invoices successfully generated");
    }

    record GenerateStudentInvoiceRequest(Long studentId){}
    record GenerateSchoolClassInvoiceRequest(Long schoolClassId){}

}
