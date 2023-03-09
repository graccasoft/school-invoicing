package com.graccasoft.schoolinvoicing.dto;

import com.graccasoft.schoolinvoicing.model.InvoiceItem;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class InvoiceItemDtoMapper implements Function<InvoiceItem, InvoiceItemDto> {
    @Override
    public InvoiceItemDto apply(InvoiceItem invoiceItem) {
        return InvoiceItemDto.builder()
                .quantity(invoiceItem.getQuantity())
                .unitPrice(invoiceItem.getUnitPrice())
                .billableDescription(invoiceItem.getBillable().getDescription())
                .billableId(invoiceItem.getBillable().getId())

                .build();
    }
}
