package model;

import jakarta.persistence.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity
public class Vol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;

    @Temporal(TemporalType.TIMESTAMP)
    private Date departureTime;  // Utilisation de Date pour l'heure de départ

    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalTime;  // Utilisation de Date pour l'heure d'arrivée

    @Temporal(TemporalType.DATE)
    private Date departureDate;  // Utilisation de Date pour la date de départ

    private String departureAirport;
    private String arrivalAirport;
    private double price;
    private int availableTickets;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }
    public Vol(){

    }
    // Méthode de formatage de la date

    public String getFormattedDepartureTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE. d MMM yyyy HH:mm", Locale.FRENCH);
        return formatter.format(departureTime);
    }

    public String getFormattedArrivalTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE. d MMM yyyy HH:mm", Locale.FRENCH);
        return formatter.format(arrivalTime);
    }
}
