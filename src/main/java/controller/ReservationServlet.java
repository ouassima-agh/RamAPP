package controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.Flight;
import model.Passenger;
import model.Reservation;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/reserve")
public class ReservationServlet extends HttpServlet {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // 🧍 Création du passager
            Passenger p = new Passenger();
            p.setNom(request.getParameter("nom"));
            p.setPrenom(request.getParameter("prenom"));
            p.setEmail(request.getParameter("email"));
            p.setTelephone(request.getParameter("telephone"));
            p.setSexe(request.getParameter("sexe"));
            p.setDateNaissance(LocalDate.parse(request.getParameter("dob")));
            p.setPassword("temp123"); // à remplacer plus tard par un vrai système de mot de passe

            em.persist(p);

            // ✈️ Récupération du vol
            Long flightId = Long.parseLong(request.getParameter("flightId"));
            Flight f = em.find(Flight.class, flightId);
            if (f.getAvailableTickets() <= 0) {
                throw new ServletException("Plus de billets disponibles !");
            }

            f.setAvailableTickets(f.getAvailableTickets() - 1);
            em.merge(f);

            // 🧾 Création de la réservation
            Reservation r = new Reservation();
            r.setPassenger(p);
            r.setFlight(f);
            r.setPrice(f.getPrice());
            r.setDate(LocalDate.now());

            em.persist(r);

            em.getTransaction().commit();

            request.setAttribute("reservation", r);
            request.getRequestDispatcher("reservationConfirmation.jsp").forward(request, response);

        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new ServletException("Erreur : " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
}
