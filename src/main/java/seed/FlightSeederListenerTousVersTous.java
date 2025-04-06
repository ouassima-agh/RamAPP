package seed;

import dao.AirportDAO;
import dao.FlightDAO;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import model.Airport;
import model.Flight;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@WebListener
public class FlightSeederListenerTousVersTous implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("🚀 [Seeder] Génération vols tous-vers-tous (7 jours)");

        AirportDAO airportDAO = new AirportDAO();
        FlightDAO flightDAO = new FlightDAO();

        List<Airport> airports = airportDAO.getAllAirports();

        if (airports == null || airports.size() < 2) {
            System.err.println("❌ Pas assez d’aéroports pour générer des vols.");
            return;
        }

        int flightNum = 1000;
        Random rand = new Random();

        for (int dayOffset = 0; dayOffset < 7; dayOffset++) {
            LocalDate date = LocalDate.now().plusDays(dayOffset);

            for (Airport dep : airports) {
                for (Airport arr : airports) {
                    if (dep.getId().equals(arr.getId())) continue; // évite les doublons vers soi-même

                    String flightCode = "RAM" + flightNum++;

                    if (flightDAO.getFlightByNumber(flightCode) != null) continue;

                    int hour = 6 + rand.nextInt(10);
                    String depTime = String.format("%02d:00", hour);
                    String arrTime = String.format("%02d:00", hour + 2);

                    Flight flight = new Flight();
                    flight.setFlightNumber(flightCode);
                    flight.setDepartureAirport(dep);
                    flight.setArrivalAirport(arr);
                    flight.setDepartureDate(date.toString());
                    flight.setDepartureTime(depTime);
                    flight.setArrivalTime(arrTime);
                    flight.setPrice(150 + rand.nextInt(300));
                    flight.setAvailableTickets(60 + rand.nextInt(40));

                    flightDAO.addFlight(flight);
                    System.out.println("✈️ Vol ajouté : " + flightCode + " | " + dep.getCity() + " ➝ " + arr.getCity() + " | " + date);
                }
            }
        }

        System.out.println("✅ Vols tous-vers-tous générés.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Rien à faire ici
    }
}
