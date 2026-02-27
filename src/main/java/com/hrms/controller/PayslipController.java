package com.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.dto.PayslipDTO;
import com.hrms.service.PayslipService;
import com.hrms.util.PdfGenerator;
import com.itextpdf.text.DocumentException;

@RestController
public class PayslipController {

    @Autowired
    private PayslipService payslipService;

    @GetMapping("/api/payslips/download")
    public ResponseEntity<byte[]> downloadPayslip(@RequestParam String id) {
        try {
            
            PayslipDTO slip = payslipService.getPayslipById(id);
            if (slip == null) {
                return ResponseEntity.notFound().build();
            }

            byte[] pdfBytes = PdfGenerator.generatePayslipPdf(slip);        
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData(
                "attachment", "Payslip-" + slip.getMonth() + "-" + slip.getYear() + ".pdf"
            );

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);

        } catch (DocumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

}
