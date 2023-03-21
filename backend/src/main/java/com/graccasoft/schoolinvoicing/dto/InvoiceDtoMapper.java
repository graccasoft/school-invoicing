package com.graccasoft.schoolinvoicing.dto;

import java.util.function.Function;
import com.graccasoft.schoolinvoicing.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceDtoMapper implements Function<Invoice, InvoiceDto> {

    @Autowired
    private InvoiceItemDtoMapper invoiceItemDtoMapper;

    @Override
    public InvoiceDto apply(Invoice invoice) {
        return InvoiceDto.builder()
                .items(invoice.getItems().stream().map(invoiceItemDtoMapper).toList())
                .studentId(invoice.getStudent().getId())
                .schoolClassId(invoice.getStudent().getSchoolClass().getId())
                .studentName(invoice.getStudent().toString())
                .schoolClassName(invoice.getStudent().getSchoolClass().getDescription())
                .totalAmount(invoice.getTotalAmount())
                .createdAt(invoice.getCreatedAt())
                .id(invoice.getId())
                .title(invoice.getTitle())
                .build();
    }
}
