package com.graccasoft.schoolinvoicing.service;

import com.graccasoft.schoolinvoicing.dto.InvoiceDto;

import java.util.List;

public interface InvoiceService {
    List<InvoiceDto> getSchoolClassInvoices(Long schoolClassId);
    List<InvoiceDto> getStudentInvoices(Long studentId);
    InvoiceDto getInvoice(Long invoiceId);
    void generateInvoiceForSchoolClass(Long schoolClassId);
    void generateInvoiceForStudent(Long studentId);
}
