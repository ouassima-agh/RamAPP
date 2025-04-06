package controller;

import dao.AirportDAO;
import dao.FlightDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Airport;
import model.Flight;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/searchFlights")
public class SearchFlightServlet extends HttpServlet {
    private FlightDAO flightDAO = new FlightDAO();
    private AirportDAO airportDAO = new AirportDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String departIdStr = request.getParameter("departureAirport");
        String arriveeIdStr = request.getParameter("arrivalAirport");
        String departDate = request.getParameter("departDate");
        String departTime = request.getParameter("departTime");
        String tripType = request.getParameter("tripType");
        String returnDate = request.getParameter("returnDate");
        String returnTime = request.getParameter("returnTime");

        // Récupération des objets Airport à partir des ID
        final Airport departAirport = (departIdStr != null && !departIdStr.isEmpty())
                ? airportDAO.getAirportById(Long.parseLong(departIdStr))
                : null;

        final Airport arriveeAirport = (arriveeIdStr != null && !arriveeIdStr.isEmpty())
                ? airportDAO.getAirportById(Long.parseLong(arriveeIdStr))
                : null;

        // Récupération de tous les vols
        List<Flight> allFlights = flightDAO.getAllFlights();
        System.out.println("🔎 getAllFlights() → taille = " + allFlights.size());

        // 🔎 DEBUG : Affichage brut de tous les vols
        System.out.println("📦 Tous les vols :");
        for (Flight f : allFlights) {
            System.out.println("→ " + f.getFlightNumber() + " | " + f.getDepartureAirport().getId()
                    + " ➜ " + f.getArrivalAirport().getId() + " | " + f.getDepartureDate() + " " + f.getDepartureTime());
        }

        // 🔍 Filtres des vols aller
        List<Flight> allerFlights = allFlights.stream()
                .filter(f -> departAirport == null || (f.getDepartureAirport() != null &&
                        f.getDepartureAirport().getId().equals(departAirport.getId())))
                .filter(f -> arriveeAirport == null || (f.getArrivalAirport() != null &&
                        f.getArrivalAirport().getId().equals(arriveeAirport.getId())))
                .filter(f -> departDate == null || departDate.isEmpty()
                        || f.getDepartureDate().trim().equals(departDate.trim()))
                .filter(f -> departTime == null || departTime.isEmpty()
                        || f.getDepartureTime().startsWith(departTime.trim()))
                .collect(Collectors.toList());

        System.out.println("🟢 Vols aller trouvés : " + allerFlights.size());
        for (Flight f : allerFlights) {
            System.out.println("✔ Aller : " + f.getFlightNumber());
        }

        request.setAttribute("allerFlights", allerFlights);

        // 🔁 Si aller-retour, filtrer les vols retour
        if ("roundTrip".equals(tripType) && returnDate != null && !returnDate.isEmpty()) {
            List<Flight> retourFlights = allFlights.stream()
                    .filter(f -> departAirport == null || (f.getArrivalAirport() != null &&
                            f.getArrivalAirport().getId().equals(departAirport.getId())))
                    .filter(f -> arriveeAirport == null || (f.getDepartureAirport() != null &&
                            f.getDepartureAirport().getId().equals(arriveeAirport.getId())))
                    .filter(f -> returnDate == null || returnDate.isEmpty()
                            || f.getDepartureDate().trim().equals(returnDate.trim()))
                    .filter(f -> returnTime == null || returnTime.isEmpty()
                            || f.getDepartureTime().startsWith(returnTime.trim()))
                    .collect(Collectors.toList());

            System.out.println("🟣 Vols retour trouvés : " + retourFlights.size());
            for (Flight f : retourFlights) {
                System.out.println("✔ Retour : " + f.getFlightNumber());
            }

            request.setAttribute("retourFlights", retourFlights);
        }

        // 🚀 Redirection vers la JSP
        request.getRequestDispatcher("resultats.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Airport> airports = airportDAO.getAllAirports();
        request.setAttribute("airports", airports);
        request.getRequestDispatcher("indexa.jsp").forward(request, response);
    }
}
