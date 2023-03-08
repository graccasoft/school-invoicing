package com.graccasoft.schoolinvoicing.service.impl;

import com.graccasoft.schoolinvoicing.dto.InvoiceDto;
import com.graccasoft.schoolinvoicing.dto.InvoiceDtoMapper;
import com.graccasoft.schoolinvoicing.model.Invoice;
import com.graccasoft.schoolinvoicing.repository.InvoiceRepository;
import com.graccasoft.schoolinvoicing.service.InvoiceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceDtoMapper invoiceDtoMapper;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceDtoMapper invoiceDtoMapper) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceDtoMapper = invoiceDtoMapper;
    }

    @Override
    public List<InvoiceDto> getSchoolClassInvoices(Long schoolClassId) {

        return invoiceRepository.findBySchoolClass(schoolClassId)
                .stream()
                .map(invoiceDtoMapper)
                .toList();
    }

    @Override
    public List<InvoiceDto> getStudentInvoices(Long studentId) {
        return invoiceRepository.findByStudent(studentId)
                .stream()
                .map(invoiceDtoMapper)
                .toList();
    }

    @Override
    public InvoiceDto getInvoice(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(()->new EntityNotFoundException("Invoice not found"));
        return invoiceDtoMapper.apply( invoice );
    }

    @Override
    @Transactional
    public void generateInvoiceForSchoolClass(Long schoolClassId) {

    }

    @Override
    @Transactional
    public void generateInvoiceForStudent(Long studentId) {

    }
}
