import model.Vol;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Date;

public class test {

    public static void main(String[] args) {
        // Créer une EntityManagerFactory avec l'unité de persistance "vol2"
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
        EntityManager em = emf.createEntityManager();

        // Commencer une transaction
        em.getTransaction().begin();

        // Créer un vol
        Vol flight = new Vol();
        flight.setFlightNumber("AF123");
        flight.setDepartureDate(new Date());
        flight.setDepartureTime(new Date());
        flight.setArrivalTime(new Date());
        flight.setDepartureAirport("Airport 1");
        flight.setArrivalAirport("AIRPORT2");
        flight.setPrice(450.75);
        flight.setAvailableTickets(120);

        // Sauvegarder dans la base de données
        em.persist(flight);

        // Valider la transaction
        em.getTransaction().commit();

        // Vérifier l'insertion en récupérant le vol
        Vol savedFlight = em.find(Vol.class, flight.getId());
        System.out.println("Vol enregistré : " + savedFlight.getFlightNumber());

        // Fermer l'EntityManager
        em.close();
        emf.close();
    }

}

