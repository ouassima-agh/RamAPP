package model;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;

public class PdfGenerator {

    public static String generateTicket(Reservation reservation, String outputPath) throws Exception {
        String fileName = "ticket_" + reservation.getId() + ".pdf";
        File pdfFile = new File(outputPath, fileName);

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
        document.open();

        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Font bodyFont = new Font(Font.FontFamily.HELVETICA, 12);

        document.add(new Paragraph("Confirmation de Réservation", titleFont));
        document.add(new Paragraph(" "));

        document.add(new Paragraph("ID Réservation : " + reservation.getId(), bodyFont));
        document.add(new Paragraph("Passager : " + reservation.getPassenger().getPrenom() + " " + reservation.getPassenger().getNom(), bodyFont));
        document.add(new Paragraph("Vol : " + reservation.getFlight().getFlightNumber(), bodyFont));
        document.add(new Paragraph("Départ : " + reservation.getFlight().getDepartureAirport().getCity(), bodyFont));
        document.add(new Paragraph("Arrivée : " + reservation.getFlight().getArrivalAirport().getCity(), bodyFont));
        document.add(new Paragraph("Date : " + reservation.getFlight().getDepartureDate(), bodyFont));
        document.add(new Paragraph("Montant : " + reservation.getPrice() + " MAD", bodyFont));

        document.close();

        return "pdfs/" + fileName; // Chemin relatif pour lien JSP
    }
}
