package com.graccasoft.schoolinvoicing.service.impl;

import com.graccasoft.schoolinvoicing.model.Invoice;
import com.graccasoft.schoolinvoicing.repository.InvoiceRepository;
import com.graccasoft.schoolinvoicing.service.InvoiceDocumentService;
import com.graccasoft.schoolinvoicing.service.PdfGeneratorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Service
public class InvoiceDocumentServiceImpl implements InvoiceDocumentService {
    private final InvoiceRepository invoiceRepository;
    private final PdfGeneratorService pdfGeneratorService;

    public InvoiceDocumentServiceImpl(InvoiceRepository invoiceRepository, PdfGeneratorService pdfGeneratorService) {
        this.invoiceRepository = invoiceRepository;
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @Override
    public String getInvoiceHtml(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow( ()-> new EntityNotFoundException("Invoice not found"));
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("invoice", invoice);

        return templateEngine.process("invoice", context);
    }

    @Override
    public String getInvoiceFileName(Long invoiceId) {

        String pdfFile = "invoice-" + invoiceId + ".pdf";
        return pdfGeneratorService.generatePdfFromHtml( getInvoiceHtml(invoiceId), pdfFile );
    }
}
