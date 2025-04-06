package dao;


import model.Vol;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class VolDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

    public List<Vol> getAllFlight() {
        EntityManager em = emf.createEntityManager();
        List<Vol> flights = null;
        try {
            flights = em.createQuery("SELECT v FROM Vol v ORDER BY v.id DESC", Vol.class).getResultList();
        } finally {
            em.close();
        }
        return flights;
    }

    public void deleteVoyage(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Vol flight = em.find(Vol.class, id);
            if (flight != null) {
                em.remove(flight);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void addVoyage(Vol flight) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(flight);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Vol getFlightById(Long id) {
        EntityManager em = emf.createEntityManager();
        Vol flight = null;
        try {
            flight = em.find(Vol.class, id);
        } finally {
            em.close();
        }
        return flight;
    }
}