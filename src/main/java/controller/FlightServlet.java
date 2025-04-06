package controller;

import dao.VolDAO;
import model.Vol;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/FlightServlet")
public class FlightServlet extends HttpServlet {
    private VolDAO flightDAO;

    public void init() {
        flightDAO = new VolDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("addFlight.jsp");
            dispatcher.forward(request, response);
        } else {
            List<Vol> flights = flightDAO.getAllFlight();
            request.setAttribute("flights", flights);
            RequestDispatcher dispatcher = request.getRequestDispatcher("flight.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("delete");

        if (action != null) {
            Long id = Long.parseLong(request.getParameter("id"));
            flightDAO.deleteVoyage(id);
            response.sendRedirect("FlightServlet");
        } else {
            try {
                // Récupérer les paramètres du formulaire
                String flightNumber = request.getParameter("flightNumber");
                String departureTimeStr = request.getParameter("departureTime");  // Récupération du champ datetime-local
                String arrivalTimeStr = request.getParameter("arrivalTime");      // Récupération du champ datetime-local

                // Création du format SimpleDateFormat pour analyser la date et l'heure
                SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

                // Parse les chaînes de texte en objets Date
                Date departureTime = datetimeFormat.parse(departureTimeStr);
                Date arrivalTime = datetimeFormat.parse(arrivalTimeStr);

                // Récupérer d'autres champs du formulaire
                double price = Double.parseDouble(request.getParameter("price"));
                int availableTickets = Integer.parseInt(request.getParameter("availableTickets"));

                // Créer un objet Vol et y assigner les valeurs
                Vol flight = new Vol();
                flight.setFlightNumber(flightNumber);
                flight.setDepartureTime(departureTime);
                flight.setArrivalTime(arrivalTime);
                flight.setPrice(price);
                flight.setAvailableTickets(availableTickets);

                // Ajouter le vol dans la base de données
                flightDAO.addVoyage(flight);

                // Rediriger vers la servlet pour afficher les vols
                response.sendRedirect("FlightServlet");

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Erreur lors de l'ajout du vol : " + e.getMessage());
                request.getRequestDispatcher("addFlight.jsp").forward(request, response);
            }
        }
    }
}