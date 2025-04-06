package model;

import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 Lien avec Passager (Many reservations to one passenger)
    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;

    // 🔗 Lien avec Vol (Many reservations to one flight)
    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @Column(nullable = false)
    private double price;

    @Column(name = "reservation_date", nullable = false)
    private LocalDate date;

    // --- Constructeurs ---
    public Reservation() {}

    public Reservation(Passenger passenger, Flight flight, double price, LocalDate date) {
        this.passenger = passenger;
        this.flight = flight;
        this.price = price;
        this.date = date;
    }

    public Reservation(Passenger passenger, double price, LocalDate now) {

        this.passenger=passenger;
        this.price=price;

    }

    // --- Getters & Setters ---
    public Long getId() {
        return id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}