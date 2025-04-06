package seed;

import dao.AirportDAO;
import dao.FlightDAO;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import model.Airport;
import model.Flight;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@WebListener
public class FlightSeederListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("🔁 [Seeder] Initialisation des vols...");

        FlightDAO flightDAO = new FlightDAO();
        AirportDAO airportDAO = new AirportDAO();

        // Aéroport de départ : Casablanca (ID 3)
        Airport casablanca = airportDAO.getAirportById(3L);

        if (casablanca == null) {
            System.err.println("❌ [Seeder] Aéroport Casablanca introuvable (ID 3)");
            return;
        }

        // Destinations (à adapter selon tes ID de ta table `airports`)
        List<Long> destinationIds = Arrays.asList(1L, 2L, 4L, 5L, 6L, 7L, 8L, 9L);

        int flightNum = 300;
        int dayOffset = 0;
        Random random = new Random();

        for (Long destId : destinationIds) {
            try {
                Airport destination = airportDAO.getAirportById(destId);
                if (destination == null) {
                    System.out.println("⚠️ [Seeder] Aéroport non trouvé pour ID: " + destId);
                    continue;
                }

                Flight flight = new Flight();
                flight.setFlightNumber("RAM" + flightNum++);
                flight.setDepartureAirport(casablanca);
                flight.setArrivalAirport(destination);

                // Date de départ = aujourd’hui + X jours
                LocalDate date = LocalDate.now().plusDays(dayOffset++);
                flight.setDepartureDate(date.toString());

                // Heures et prix dynamiques
                int hour = 6 + random.nextInt(10); // entre 6h et 16h
                String depTime = String.format("%02d:00", hour);
                String arrTime = String.format("%02d:00", hour + 2);

                flight.setDepartureTime(depTime);
                flight.setArrivalTime(arrTime);
                flight.setPrice(150 + random.nextInt(200));
                flight.setAvailableTickets(60 + random.nextInt(40));

                flightDAO.addFlight(flight);
                System.out.println("✅ Vol ajouté : " + flight.getFlightNumber() + " → " + destination.getCity());

            } catch (Exception e) {
                System.err.println("❌ Erreur lors de l'ajout du vol : " + e.getMessage());
                e.printStackTrace();
            }
        }

        System.out.println("✅ [Seeder] Vols générés avec succès.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Rien à nettoyer ici
    }
}
