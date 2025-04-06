package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Airport;

import java.util.List;

public class AirportDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

    // ✅ Récupérer tous les aéroports
    public List<Airport> getAllAirports() {
        EntityManager em = emf.createEntityManager();
        List<Airport> airports = null;
        try {
            airports = em.createQuery("SELECT a FROM Airport a", Airport.class).getResultList();
        } finally {
            em.close();
        }
        return airports;
    }

    // ✅ Récupérer un aéroport par ID
    public Airport getAirportById(Long id) {
        EntityManager em = emf.createEntityManager();
        Airport airport = null;
        try {
            airport = em.find(Airport.class, id);
        } finally {
            em.close();
        }
        return airport;
    }
}
