package model;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class PdfGenerator {

    // Couleurs modernes
    private static final BaseColor PRIMARY_COLOR = new BaseColor(0, 71, 171);
    private static final BaseColor SECONDARY_COLOR = new BaseColor(245, 247, 250);
    private static final BaseColor ACCENT_COLOR = new BaseColor(255, 102, 0);
    private static final BaseColor TEXT_COLOR = new BaseColor(51, 51, 51);

    public static String generateTicket(Reservation reservation, String outputPath) throws Exception {
        String fileName = "boardingpass_" + reservation.getId() + ".pdf";
        File pdfFile = new File(outputPath, fileName);

        // Créer le répertoire si nécessaire
        pdfFile.getParentFile().mkdirs();

        // Format de page personnalisé (largeur x hauteur)
        Rectangle pageSize = new Rectangle(595, 350); // Légèrement plus haut
        Document document = new Document(pageSize, 30, 30, 20, 20);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));

        // Ajouter un fond stylisé
        writer.setPageEvent(new BoardingPassBackground());

        document.open();

        // Polices améliorées
        Font brandFont = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD, PRIMARY_COLOR);
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, PRIMARY_COLOR);
        Font sectionFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, PRIMARY_COLOR);
        Font labelFont = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD, TEXT_COLOR);
        Font valueFont = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL, TEXT_COLOR);
        Font accentFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, ACCENT_COLOR);

        // Structure principale à 2 colonnes
        PdfPTable mainTable = new PdfPTable(new float[]{0.6f, 0.4f});
        mainTable.setWidthPercentage(100);
        mainTable.setSpacingBefore(10);

        // COLONNE GAUCHE ========================================
        PdfPCell leftCell = new PdfPCell();
        leftCell.setBorder(Rectangle.RIGHT);
        leftCell.setBorderColor(new BaseColor(200, 200, 200));
        leftCell.setPaddingRight(20);
        leftCell.setPaddingLeft(10);

        // En-tête avec logo
        PdfPTable headerTable = new PdfPTable(2);
        headerTable.setWidthPercentage(100);

        // Logo (gestion inchangée)
        PdfPCell logoCell = new PdfPCell();
        logoCell.setBorder(Rectangle.NO_BORDER);
        boolean logoAdded = false;

        try {
            InputStream logoStream = PdfGenerator.class.getClassLoader().getResourceAsStream("Images/logo.png");
            if (logoStream != null) {
                Image logo = Image.getInstance(logoStream.readAllBytes());
                logo.scaleToFit(80, 40);
                logoCell.addElement(logo);
                logoAdded = true;
            }
        } catch (Exception e) {
            System.out.println("Logo non chargé: " + e.getMessage());
        }

        if (!logoAdded) {
            Paragraph logoText = new Paragraph("AERO MAROC", brandFont);
            logoCell.addElement(logoText);
        }

        headerTable.addCell(logoCell);

        // Titre à droite
//        PdfPCell titleCell = new PdfPCell(new Phrase("CARTE D'EMBARQUEMENT", titleFont));
//        titleCell.setBorder(Rectangle.NO_BORDER);
//        titleCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        Paragraph titleParagraph = new Paragraph("CARTE D'EMBARQUEMENT", titleFont);
        titleParagraph.setAlignment(Element.ALIGN_RIGHT);
        PdfPCell titleCell = new PdfPCell(titleParagraph);
        titleCell.setBorder(Rectangle.NO_BORDER);
        titleCell.setPaddingLeft(20); // Espacement depuis le logo
        titleCell.setPaddingBottom(5);
        titleCell.setFixedHeight(40); // Hauteur fixe pour alignement
        titleCell.setNoWrap(true); // EMPÊCHE LE RETOUR À LA LIGNE
        headerTable.addCell(titleCell);

        leftCell.addElement(headerTable);

        // Séparateur
        leftCell.addElement(new Chunk(new LineSeparator(1, 100, PRIMARY_COLOR, Element.ALIGN_CENTER, -2)));

        // Informations de vol
        PdfPTable flightTable = new PdfPTable(2);
        flightTable.setWidthPercentage(100);
        flightTable.setSpacingAfter(15);

        // Départ
        PdfPCell depCell = new PdfPCell();
        depCell.setBorder(Rectangle.NO_BORDER);
        depCell.addElement(new Phrase("DÉPART", labelFont));
        depCell.addElement(new Phrase(reservation.getFlight().getDepartureAirport().getCity(), sectionFont));
        depCell.addElement(new Phrase("Date: " + reservation.getFlight().getDepartureDate(), valueFont));
        depCell.addElement(new Phrase("Vol: " + reservation.getFlight().getFlightNumber(), valueFont));
        flightTable.addCell(depCell);

        // Arrivée
        PdfPCell arrCell = new PdfPCell();
        arrCell.setBorder(Rectangle.NO_BORDER);
        arrCell.addElement(new Phrase("ARRIVÉE", labelFont));
        arrCell.addElement(new Phrase(reservation.getFlight().getArrivalAirport().getCity(), sectionFont));
        arrCell.addElement(new Phrase("Terminal: -", valueFont));
        flightTable.addCell(arrCell);

        leftCell.addElement(flightTable);

        // Informations passager
        leftCell.addElement(createSection("PASSAGER",
                reservation.getPassenger().getPrenom() + " " + reservation.getPassenger().getNom(),
                labelFont, sectionFont));

        // Informations réservation
        leftCell.addElement(createSection("RÉSERVATION",
                "Ref: " + reservation.getId() + " | Prix: " + reservation.getPrice() + " €",
                labelFont, accentFont));

        // COLONNE DROITE ========================================
        PdfPCell rightCell = new PdfPCell();
        rightCell.setBackgroundColor(SECONDARY_COLOR);
        rightCell.setPadding(15);
        rightCell.setBorder(Rectangle.NO_BORDER);

        // Section Embarquement
        rightCell.addElement(createSection("EMBARQUEMENT", "", labelFont, sectionFont));

        PdfPTable boardingTable = new PdfPTable(2);
        boardingTable.setWidthPercentage(90);
        boardingTable.setSpacingAfter(15);

        addBoardingRow(boardingTable, "Porte", "A3", labelFont, valueFont);
        addBoardingRow(boardingTable, "Siège", "14B", labelFont, valueFont);
        addBoardingRow(boardingTable, "Groupe", "2", labelFont, valueFont);
        addBoardingRow(boardingTable, "Priorité", "Standard", labelFont, valueFont);

        rightCell.addElement(boardingTable);

        // Code-barres simulé
        PdfContentByte cb = writer.getDirectContent();
        float barStartX = document.right() - 120;
        float barStartY = document.bottom() + 80;

        cb.rectangle(barStartX, barStartY, 120, 40);
        cb.stroke();

        Paragraph barcodeText = new Paragraph("" + reservation.getId() + "",
                new Font(Font.FontFamily.COURIER, 14, Font.BOLD));
        barcodeText.setAlignment(Element.ALIGN_CENTER);
        rightCell.addElement(barcodeText);

        // Footer
        Paragraph footer = new Paragraph("Embarquement ferme 30 min avant le départ",
                new Font(Font.FontFamily.HELVETICA, 8, Font.ITALIC, BaseColor.GRAY));
        footer.setAlignment(Element.ALIGN_CENTER);
        rightCell.addElement(footer);

        // Assemblage final
        mainTable.addCell(leftCell);
        mainTable.addCell(rightCell);
        document.add(mainTable);

        document.close();

        return "pdfs/" + fileName;
    }

    // Méthodes utilitaires
    private static Paragraph createSection(String title, String content, Font labelFont, Font contentFont) {
        Paragraph section = new Paragraph();
        section.add(new Phrase(title + "\n", labelFont));
        section.add(new Phrase(content + "\n", contentFont));
        section.setSpacingAfter(10);
        return section;
    }

    private static void addBoardingRow(PdfPTable table, String label, String value, Font labelFont, Font valueFont) {
        table.addCell(new Phrase(label, labelFont));
        PdfPCell valueCell = new PdfPCell(new Phrase(value, valueFont));
        valueCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        valueCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(valueCell);
    }

    // Fond stylisé
    static class BoardingPassBackground extends PdfPageEventHelper {
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte canvas = writer.getDirectContentUnder();
            Rectangle rect = document.getPageSize();

            // Fond léger
            canvas.setColorFill(SECONDARY_COLOR);
            canvas.rectangle(rect.getLeft(), rect.getBottom(), rect.getWidth(), rect.getHeight());
            canvas.fill();

            // Bordure supérieure
            canvas.setColorFill(PRIMARY_COLOR);
            canvas.rectangle(rect.getLeft(), rect.getTop() - 8, rect.getWidth(), 8);
            canvas.fill();
        }
    }
}