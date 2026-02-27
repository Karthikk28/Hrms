package com.hrms.util;

import java.io.ByteArrayOutputStream;

import com.hrms.dto.PayslipDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class PdfGenerator {

    public static byte[] generatePayslipPdf(PayslipDTO slip) throws DocumentException {
        Document document = new Document(PageSize.A4, 36, 36, 36, 36);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, out);
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLUE);
        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        Font regularFont = FontFactory.getFont(FontFactory.HELVETICA, 11);

        document.add(new Paragraph(slip.getMonth() + " Payslip", titleFont));
        document.add(Chunk.NEWLINE);

        PdfPTable empTable = new PdfPTable(2);
        empTable.setWidthPercentage(100);
        empTable.setWidths(new int[]{1, 2});

        empTable.addCell(getCell("Employee Name", headFont, PdfPCell.ALIGN_LEFT));
        empTable.addCell(getCell(slip.getEmployee().getFirstName(), regularFont, PdfPCell.ALIGN_LEFT));

        empTable.addCell(getCell("Employee ID", headFont, PdfPCell.ALIGN_LEFT));
        empTable.addCell(getCell(slip.getEmployee().getId().toString(), regularFont, PdfPCell.ALIGN_LEFT));

      

        empTable.addCell(getCell("Designation", headFont, PdfPCell.ALIGN_LEFT));
        empTable.addCell(getCell(slip.getEmployee().getDesignation(), regularFont, PdfPCell.ALIGN_LEFT));

        document.add(empTable);
        document.add(Chunk.NEWLINE);

        PdfPTable earningsTable = new PdfPTable(2);
        earningsTable.setWidthPercentage(100);
        earningsTable.setWidths(new int[]{3, 1});

        PdfPCell eHead = new PdfPCell(new Phrase("Earnings", headFont));
        eHead.setColspan(2);
        eHead.setBackgroundColor(BaseColor.LIGHT_GRAY);
        eHead.setHorizontalAlignment(Element.ALIGN_CENTER);
        earningsTable.addCell(eHead);

        slip.getEarnings().forEach(e -> {
            earningsTable.addCell(new Phrase(e.getLabel(), regularFont));
            earningsTable.addCell(new Phrase("₹ " + e.getAmount(), regularFont));
        });

        earningsTable.addCell(new Phrase("Total Earnings", headFont));
        earningsTable.addCell(new Phrase("₹ " + slip.getStats().getGross(), headFont));

        document.add(earningsTable);
        document.add(Chunk.NEWLINE);

        PdfPTable deductionsTable = new PdfPTable(2);
        deductionsTable.setWidthPercentage(100);
        deductionsTable.setWidths(new int[]{3, 1});

        PdfPCell dHead = new PdfPCell(new Phrase("Deductions", headFont));
        dHead.setColspan(2);
        dHead.setBackgroundColor(BaseColor.LIGHT_GRAY);
        dHead.setHorizontalAlignment(Element.ALIGN_CENTER);
        deductionsTable.addCell(dHead);

        slip.getDeductions().forEach(d -> {
            deductionsTable.addCell(new Phrase(d.getLabel(), regularFont));
            deductionsTable.addCell(new Phrase("₹ " + d.getAmount(), regularFont));
        });

        deductionsTable.addCell(new Phrase("Total Deductions", headFont));
        deductionsTable.addCell(new Phrase("₹ " + slip.getStats().getTotalDeductions(), headFont));

        document.add(deductionsTable);
        document.add(Chunk.NEWLINE);

        Paragraph netPay = new Paragraph("Net Pay: ₹ " + slip.getStats().getNetPay(), titleFont);
        netPay.setAlignment(Element.ALIGN_RIGHT);
        document.add(netPay);

        document.close();
        return out.toByteArray();
    }

    private static PdfPCell getCell(String text, Font font, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setPadding(5);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }
}
