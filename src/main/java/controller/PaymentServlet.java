package controller;

import dao.PaymentDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPU");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            String reservationIdStr = request.getParameter("reservationId");
            if (reservationIdStr == null || reservationIdStr.isEmpty()) {
                throw new ServletException("ID de réservation manquant");
            }

            Long reservationId = Long.parseLong(reservationIdStr);
            Reservation reservation = em.find(Reservation.class, reservationId);

            if (reservation == null) {
                throw new ServletException("Réservation non trouvée");
            }

            request.setAttribute("reservation", reservation);
            request.getRequestDispatcher("/paymentForm.jsp").forward(request, response);

        } catch (Exception e) {
            throw new ServletException("Erreur lors de la préparation du paiement : " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = entityManagerFactory.createEntityManager();
        PaymentDAO paymentDAO = new PaymentDAO();

        try {
            em.getTransaction().begin();

            // Récupération des champs du formulaire
            String cardNumberStr = request.getParameter("cardNumber");
            String cardHolderName = request.getParameter("cardHolderName");
            String expiryMonthStr = request.getParameter("expiryMonth");
            String expiryYearStr = request.getParameter("expiryYear");
            String cvvStr = request.getParameter("cvv");
            String reservationIdStr = request.getParameter("reservationId");

            if (cardNumberStr == null || cardHolderName == null || expiryMonthStr == null ||
                    expiryYearStr == null || cvvStr == null || reservationIdStr == null) {
                throw new ServletException("Tous les champs doivent être remplis");
            }

            Long cardNumber = Long.parseLong(cardNumberStr);
            int expiryMonth = Integer.parseInt(expiryMonthStr);
            int expiryYear = Integer.parseInt(expiryYearStr);
            Long cvv = Long.parseLong(cvvStr);
            Long reservationId = Long.parseLong(reservationIdStr);

            Reservation reservation = em.find(Reservation.class, reservationId);
            if (reservation == null) {
                throw new ServletException("Réservation non trouvée");
            }

            CarteBancaire carte = paymentDAO.findCard(cardNumber, cardHolderName, expiryMonth, expiryYear, cvv);
            if (carte == null) {
                throw new ServletException("Carte bancaire non reconnue");
            }

            // Création du paiement
            Payment payment = new Payment();
            payment.setCarteBancaire(carte);
            payment.setReservation(reservation);
            payment.setPaymentDate(new Date());
            payment.setStatus(PaymentStatus.COMPLETED);
            payment.setTransactionId(UUID.randomUUID().toString());
            payment.setTicketSent(true);

            em.persist(payment);

            // Génération du PDF (sans email)
            String pdfPath = getServletContext().getRealPath("/pdfs");
            File pdfDir = new File(pdfPath);
            if (!pdfDir.exists()) pdfDir.mkdirs();

            String pdfFile = PdfGenerator.generateTicket(reservation, pdfPath);

            em.getTransaction().commit();

            request.setAttribute("reservation", reservation);
            request.setAttribute("payment", payment);
            request.setAttribute("pdfFile", pdfFile); // Chemin relatif

            request.getRequestDispatcher("/confirmationPayment.jsp").forward(request, response);

        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new ServletException("Erreur paiement : " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    @Override
    public void destroy() {
        PaymentDAO.closeFactory(); // Fermer l'EntityManagerFactory proprement
    }
}
