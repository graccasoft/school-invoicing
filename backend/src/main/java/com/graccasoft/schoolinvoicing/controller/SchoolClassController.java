package com.graccasoft.schoolinvoicing.controller;

import com.graccasoft.schoolinvoicing.dto.BillableDto;
import com.graccasoft.schoolinvoicing.dto.InvoiceDto;
import com.graccasoft.schoolinvoicing.dto.StudentDto;
import com.graccasoft.schoolinvoicing.model.SchoolClass;
import com.graccasoft.schoolinvoicing.service.BillableService;
import com.graccasoft.schoolinvoicing.service.InvoiceService;
import com.graccasoft.schoolinvoicing.service.SchoolClassService;
import com.graccasoft.schoolinvoicing.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("school-class")
public class SchoolClassController {

    private final SchoolClassService schoolClassService;
    private final InvoiceService invoiceService;
    private final StudentService studentService;
    private final BillableService billableService;

    public SchoolClassController(SchoolClassService schoolClassService, InvoiceService invoiceService, StudentService studentService, BillableService billableService) {
        this.schoolClassService = schoolClassService;
        this.invoiceService = invoiceService;
        this.studentService = studentService;
        this.billableService = billableService;
    }

    @PostMapping
    public ResponseEntity<SchoolClass> saveSchoolClass(@RequestBody SchoolClass schoolClass){
        SchoolClass savedSchoolClass = schoolClassService.saveSchoolClass(schoolClass);

        return new ResponseEntity<>(savedSchoolClass, HttpStatus.CREATED);
    }

    @GetMapping
    public List<SchoolClass> getSchoolClasses(){
        return schoolClassService.getSchoolClasses();
    }

    @GetMapping("{schoolClassId}")
    public SchoolClass getSchoolClass(@PathVariable Long schoolClassId){
        return schoolClassService.getSchoolClass(schoolClassId);
    }

    @GetMapping("{schoolClassId}/invoices")
    public List<InvoiceDto> getInvoices(@PathVariable Long schoolClassId){
        return invoiceService.getSchoolClassInvoices(schoolClassId);
    }

    @GetMapping("{schoolClassId}/students")
    public List<StudentDto> getStudents(@PathVariable Long schoolClassId){
        return studentService.findStudentsInClass(schoolClassId);
    }

    @GetMapping("{schoolClassId}/billable-items")
    public List<BillableDto> getBillableItems(@PathVariable Long schoolClassId){
        return billableService.getBillableItemsByClass(schoolClassId);
    }
}
