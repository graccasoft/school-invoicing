package com.graccasoft.schoolinvoicing.service;

public interface InvoiceDocumentService {

    String getInvoiceHtml(Long invoiceId);
    String getInvoiceFileName(Long invoiceId);

}
