package com.graccasoft.schoolinvoicing.service.impl;

import com.graccasoft.schoolinvoicing.dto.InvoiceDto;
import com.graccasoft.schoolinvoicing.repository.InvoiceRepository;
import com.graccasoft.schoolinvoicing.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<InvoiceDto> getSchoolClassInvoices(Long schoolClassId) {
        return null;
    }

    @Override
    public List<InvoiceDto> getStudentInvoices(Long studentId) {
        return null;
    }

    @Override
    public InvoiceDto getInvoice(Long invoiceId) {
        return null;
    }

    @Override
    public void generateInvoiceForSchoolClass(Long schoolClassId) {

    }

    @Override
    public void generateInvoiceForStudent(Long studentId) {

    }
}
