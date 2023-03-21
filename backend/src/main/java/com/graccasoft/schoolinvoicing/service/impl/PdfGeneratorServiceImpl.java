package com.graccasoft.schoolinvoicing.service.impl;

import com.graccasoft.schoolinvoicing.service.PdfGeneratorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @author graciousmashasha
 * @created 28/02/2023 - 5:01 pm
 */
@Service
public class PdfGeneratorServiceImpl implements PdfGeneratorService {
    @Value("${app.tmp-directory}")
    private String tmpDirectory;

    @Override
    public String generatePdfFromHtml(String html, String newFileName) {

        String outputFile = tmpDirectory + "/" + newFileName;
        try {
            OutputStream outputStream = new FileOutputStream(outputFile);
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(outputStream);
            outputStream.close();

            return outputFile;

        }catch(Exception ex){
            ex.printStackTrace();
        }

        return null;
    }
}
