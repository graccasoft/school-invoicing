package com.graccasoft.schoolinvoicing.service;

/**
 * @author graciousmashasha
 * @created 28/02/2023 - 5:01 pm
 */
public interface PdfGeneratorService {
    String generatePdfFromHtml(String html, String newFileName);
}
