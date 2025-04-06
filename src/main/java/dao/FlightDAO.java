package dao;

import jakarta.persistence.*;
import model.Flight;

import java.util.List;

public class FlightDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

    // ✅ Récupérer tous les vols
    public List<Flight> getAllFlights() {
        EntityManager em = emf.createEntityManager();
        List<Flight> flights = null;
        try {
            flights = em.createQuery(
                            "SELECT f FROM Flight f " +
                                    "JOIN FETCH f.departureAirport " +
                                    "JOIN FETCH f.arrivalAirport", Flight.class)
                    .getResultList();
        } finally {
            em.close();
        }
        return flights;
    }

    // ✅ Ajouter un vol
    public void addFlight(Flight flight) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(flight);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // ✅ Supprimer un vol par ID
    public void deleteFlight(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Flight flight = em.find(Flight.class, id);
            if (flight != null) {
                em.remove(flight);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // ✅ Trouver un vol par ID
    public Flight getFlightById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Flight.class, id);
        } finally {
            em.close();
        }
    }

    // ✅ Trouver un vol par numéro
    public Flight getFlightByNumber(String flightNumber) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT f FROM Flight f WHERE f.flightNumber = :fn", Flight.class)
                    .setParameter("fn", flightNumber)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    // ✅ Supprimer tous les vols depuis Casablanca
    public void deleteAllCasablancaFlights() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Flight f WHERE f.departureAirport.id = :id")
                    .setParameter("id", 3L) // à adapter selon ton modèle
                    .executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
