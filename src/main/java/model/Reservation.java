package model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    private String statutReservation;
    private Date dateReservation;

    private String villeDepart;

    private String villeArrive;
    private String transport;
    private String Duree;
    private int nombreVoyageurs;
    private double prix;

    @ManyToOne
    @JoinColumn(name = "idClient", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "voyage_id")  // Clé étrangère dans Reservation
    private Voyage voyage;

    public Reservation() {

    }

    public Reservation(Long idReservation, String statutReservation, Date dateReservation, Client client) {
        this.idReservation = idReservation;
        this.statutReservation = statutReservation;
        this.dateReservation = dateReservation;
        this.client = client;
    }

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public String getStatutReservation() {
        return statutReservation;
    }

    public void setStatutReservation(String statutReservation) {
        this.statutReservation = statutReservation;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public String getVilleArrive() {
        return villeArrive;
    }

    public void setVilleArrive(String villeArrive) {
        this.villeArrive = villeArrive;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getDuree() {
        return Duree;
    }

    public void setDuree(String duree) {
        Duree = duree;
    }

    public int getNombreVoyageurs() {
        return nombreVoyageurs;
    }

    public void setNombreVoyageurs(int nombreVoyageurs) {
        this.nombreVoyageurs = nombreVoyageurs;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Voyage getVoyage() {
        return voyage;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
    }
}
