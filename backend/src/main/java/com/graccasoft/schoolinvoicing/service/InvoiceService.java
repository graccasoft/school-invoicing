package com.graccasoft.schoolinvoicing.service;

import com.graccasoft.schoolinvoicing.dto.InvoiceDto;
import com.graccasoft.schoolinvoicing.model.Invoice;

import java.util.List;

public interface InvoiceService {
    List<InvoiceDto> getSchoolClassInvoices(Long schoolClassId);
    List<InvoiceDto> getStudentInvoices(Long studentId);
    InvoiceDto getInvoice(Long invoiceId);
    void generateInvoiceForSchoolClass(Long schoolClassId, String invoiceTitle);
    void generateInvoiceForStudent(Long studentId, String invoiceTitle);

    Invoice getLatestStudentInvoice(Long studentId);
}
